package com.ruyuan.cloud.es.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruyuan.cloud.es.result.EsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * es操作模板
 * @author zhonghuashishan
 **/
@Slf4j
@AllArgsConstructor
public class EsTemplateImpl implements EsTemplate {

    /**
     * es客户端
     */
    private RestHighLevelClient restHighLevelClient;


    /**
     * 初始化es索引的mappings元数据
     *
     * @param indexName           索引名称
     * @param exIndexMappingsJson mappings元数据的json （kibana写好直接把json部分拿来用即可）
     * @return 初始化结果
     */
    @Override
    public boolean initIndexMappings(String indexName, String exIndexMappingsJson) throws Exception {
        // 1、构造指定的索引是否存在的请求
        GetIndexRequest existIndexRequest = new GetIndexRequest(indexName);
        // 2、查询指定的索引是否存在
        boolean exist = restHighLevelClient.indices().exists(existIndexRequest, RequestOptions.DEFAULT);

        Request request;
        Response response;
        if (!exist) {
            // 构造索引元数据请求
            request = new Request("PUT", indexName);
            request.setJsonEntity(exIndexMappingsJson);
            // 使用es restful api来对索引进行创建
            response = restHighLevelClient.getLowLevelClient().performRequest(request);
            log.info("初始化索引mappings的结构的请求结果为 [{}]", EntityUtils.toString(response.getEntity()));
            return true;
        }
        return false;
    }

    /**
     * 保存或更新文档
     *
     * @param indexName           索引名称
     * @param documentIndexObject 文档对象
     * @param documentId          文档id
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public boolean saveOrUpdate(String indexName, Object documentIndexObject, String documentId) throws Exception {
        String userJson = JSON.toJSONString(documentIndexObject);
        IndexRequest indexRequest = new IndexRequest(indexName).source(userJson, XContentType.JSON);
        UpdateRequest updateRequest = new UpdateRequest(indexName, documentId).doc(userJson, XContentType.JSON).upsert(indexRequest);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

        String index = updateResponse.getIndex();
        String docId = updateResponse.getId();
        long version = updateResponse.getVersion();
        log.info(String.format("Document update: index=%s, docId=%s, version=%s", index, docId, version));
        return updateResponse.getResult() == DocWriteResponse.Result.CREATED || updateResponse.getResult() == DocWriteResponse.Result.UPDATED || updateResponse.getResult() == DocWriteResponse.Result.NOOP;
    }

    /**
     * 根据索引名称 索引id 查询文档
     *
     * @param indexName 索引名称
     * @param id        文档id
     * @return 查询结果
     * @throws Exception 异常
     */
    @Override
    public String queryById(String indexName, String id) throws Exception {
        GetRequest getRequest = new GetRequest(indexName, id);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        return getResponse.getSourceAsString();
    }

    /**
     * 根据docId集合查询es文档
     *
     * @param indexName 索引名称
     * @param docsIds   订单id集合
     * @return 文档结果
     * @throws Exception 异常信息
     */
    @Override
    public MultiGetItemResponse[] multiGetOrderInfo(String indexName, List<String> docsIds) throws Exception {
        MultiGetRequest multiGetRequest = new MultiGetRequest();
        // 构建批量查询条件
        for (String docId : docsIds) {
            MultiGetRequest.Item item = new MultiGetRequest.Item(indexName, docId);
            multiGetRequest.add(item);
        }
        // 发起批量查询请求
        MultiGetResponse responses = restHighLevelClient.mget(multiGetRequest, RequestOptions.DEFAULT);
        return responses.getResponses();
    }

    /**
     * 搜索结果
     *
     * @param indexName           索引名称
     * @param searchSourceBuilder 搜索条件构造器
     * @return 结果
     * @throws IOException 异常
     */
    @Override
    public EsResponse search(String indexName, SearchSourceBuilder searchSourceBuilder) throws IOException {
        // 获取返回hits
        SearchHits hits = searchHits(indexName, searchSourceBuilder);

        JSONArray jsonArray = new JSONArray();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            JSONObject jsonObject = JSONObject.parseObject(sourceAsString);
            jsonArray.add(jsonObject);
        }

        EsResponse esResponse = new EsResponse();
        esResponse.setTotal(hits.getHits().length);
        esResponse.setData(jsonArray);
        return esResponse;
    }

    public SearchHits searchHits(String indexName, SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
        if (searchSourceBuilder.from() == -1) {
            searchSourceBuilder.size(1000);
        }

        // 查询es
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 获取返回hits
        return searchResponse.getHits();
    }

    /**
     * 更新文档
     * @param indexName 索引名称
     * @param docId 文档id
     * @param o 文档值
     * @return 结果
     */
    @Override
    public boolean updateDoc(String indexName, String docId, Object o) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, docId);
        request.doc(JSON.toJSONString(o), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        return updateResponse.getResult() == DocWriteResponse.Result.CREATED || updateResponse.getResult() == DocWriteResponse.Result.UPDATED;
    }

    /**
     * 更新文档
     * @param indexName 索引名称
     * @param docId 文档id
     * @param map 文档map
     * @return 结果
     */
    @Override
    public boolean updateDoc(String indexName, String docId, Map<String, Object> map) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, docId);
        request.doc(map);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        String index = updateResponse.getIndex();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        log.info(String.format("Document update: index=%s, docId=%s, version=%s", index, id, version));
        return updateResponse.getResult() == DocWriteResponse.Result.CREATED || updateResponse.getResult() == DocWriteResponse.Result.UPDATED;
    }

    /**
     * 删除文档
     *
     * @param indexName 索引名称
     * @param docId     文档id
     * @return 结果
     */
    @Override
    public boolean deleteDoc(String indexName, String docId) throws IOException {
        DeleteRequest request = new DeleteRequest(indexName, docId);
        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
                log.error("delete doc error：{}, docId:{}", reason, docId);
            }
        }
        return true;
    }

    /**
     * 索引是否存在
     *
     * @param indexName 索引名称
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public boolean isIndexExists(String indexName) throws Exception {
        boolean exists = false;
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        getIndexRequest.humanReadable(true);
        exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        return exists;
    }

    /**
     * 文档是否存在
     *
     * @param indexName 索引名称
     * @param docId     文档id
     * @return 结果
     * @throws IOException 异常
     */
    @Override
    public boolean isDocumentExists(String indexName, String docId) throws IOException {
        GetRequest request = new GetRequest(indexName, docId);
        return restHighLevelClient.exists(request, RequestOptions.DEFAULT);
    }
}

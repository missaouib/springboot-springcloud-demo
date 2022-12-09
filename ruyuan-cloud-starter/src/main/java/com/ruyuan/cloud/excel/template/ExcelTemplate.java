package com.ruyuan.cloud.excel.template;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Excel操作模板
 *
 * @author zhonghuashishan
 */
public interface ExcelTemplate {
    /**
     * excel 导出
     *
     * @param list           数据
     * @param title          标题
     * @param sheetName      sheet名称
     * @param pojoClass      pojo类型
     * @param fileName       文件名称
     * @param isCreateHeader 是否创建表头
     * @param response       返回对象
     * @throws IOException 异常
     */
    void exportExcel(List<?> list, String title,
                     String sheetName, Class<?> pojoClass,
                     String fileName, boolean isCreateHeader,
                     HttpServletResponse response) throws IOException;

    /**
     * excel 导出
     *
     * @param list      数据
     * @param title     标题
     * @param sheetName sheet名称
     * @param pojoClass pojo类型
     * @param fileName  文件名称
     * @param response  返回对象
     * @throws IOException 异常
     */
    void exportExcel(List<?> list, String title,
                     String sheetName, Class<?> pojoClass,
                     String fileName, HttpServletResponse response) throws IOException;

    /**
     * excel 导出
     *
     * @param list         数据
     * @param pojoClass    pojo类型
     * @param fileName     文件名称
     * @param response     返回对象
     * @param exportParams 导出参数
     * @throws IOException 异常
     */
    void exportExcel(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams, HttpServletResponse response) throws IOException;

    /**
     * excel 导出
     *
     * @param list     数据
     * @param fileName 文件名称
     * @param response 返回对象
     * @throws IOException 异常
     */
    void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException;

    /**
     * excel 导入
     *
     * @param filePath   excel文件路径
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param pojoClass  pojo类型
     * @param <T>        泛型
     * @return 结果集
     * @throws IOException 异常
     */
    <T> List<T> importExcel(String filePath, Integer titleRows,
                            Integer headerRows, Class<T> pojoClass) throws IOException;

    /**
     * excel 导入
     *
     * @param excelFile excel文件
     * @param pojoClass pojo类型
     * @param <T>       泛型
     * @return 结果集
     * @throws IOException 异常
     */
    <T> List<T> importExcel(MultipartFile excelFile, Class<T> pojoClass) throws IOException;

    /**
     * excel 导入
     *
     * @param file       excel文件
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param pojoClass  pojo类型
     * @param <T>        泛型
     * @return 结果集
     * @throws IOException 异常
     */
    <T> List<T> importExcel(MultipartFile file, Integer titleRows,
                            Integer headerRows, Class<T> pojoClass) throws IOException;

    /**
     * excel 导入
     *
     * @param file       上传的文件
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param needVerify 是否检验excel内容
     * @param pojoClass  pojo类型
     * @param <T>        泛型
     * @return 结果集
     * @throws IOException 异常
     */
    <T> List<T> importExcel(MultipartFile file, Integer titleRows,
                            Integer headerRows, boolean needVerify,
                            Class<T> pojoClass) throws IOException;

    /**
     * excel 导入
     *
     * @param inputStream 文件输入流
     * @param titleRows   标题行
     * @param headerRows  表头行
     * @param needVerify  是否检验excel内容
     * @param pojoClass   pojo类型
     * @param <T>         泛型
     * @return 结果集
     * @throws IOException 异常
     */
    <T> List<T> importExcel(InputStream inputStream, Integer titleRows,
                            Integer headerRows, boolean needVerify,
                            Class<T> pojoClass) throws IOException;

}
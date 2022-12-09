package com.ruyuan.cloud.excel.cover;

import cn.afterturn.easypoi.cache.manager.IFileLoader;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * 覆盖easy poi文件加载的实现类 适配spring boot的文件路径
 *
 * 文件加载类,根据路径加载指定文件
 *
 * @author zhonghuashishan
 */
public class RuyuanFileLoader implements IFileLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuyuanFileLoader.class);

    @Override
    public byte[] getFile(String url) {
        InputStream fileIs = null;
        ByteArrayOutputStream baos = null;
        try {

            // 判断是否是网络地址
            if (url.startsWith("http")) {
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(30);
                urlConnection.setReadTimeout(60);
                urlConnection.setDoInput(true);
                fileIs = urlConnection.getInputStream();
            } else {
                // 先用绝对路径查询,再查询相对路径
                try {
                    fileIs = new FileInputStream(url);
                } catch (FileNotFoundException e) {
                    // 获取项目文件
                    fileIs = RuyuanFileLoader.class.getClassLoader().getResourceAsStream(url);
                    if (fileIs == null) {
                        fileIs = RuyuanFileLoader.class.getResourceAsStream(url);
                    }
                }
            }
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileIs.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(fileIs);
            IOUtils.closeQuietly(baos);
        }
        LOGGER.error(fileIs + "这个路径文件没有找到,请查询");
        return null;
    }
}
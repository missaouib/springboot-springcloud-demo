package com.ruyuan.cloud.excel.configuration;

import com.ruyuan.cloud.excel.properties.EasyPoiProperties;
import com.ruyuan.cloud.excel.template.ExcelTemplate;
import com.ruyuan.cloud.excel.template.ExcelTemplateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.io.File;

/**
 * easy-poi自动装配
 *
 * @author zhonghuashishan
 **/
@Configuration
@EnableConfigurationProperties(EasyPoiProperties.class)
@ConditionalOnProperty(prefix = "ruyuan.easy.poi", name = "enable", matchIfMissing = false)
@ComponentScan(basePackages = {"cn.afterturn.easypoi"}) // 扫描easypoi框架包下面的bean
public class EasyPoiAutoConfiguration {


    @Autowired
    private EasyPoiProperties easyPoiProperties;


    /**
     * 通过 order 属性来定义视图解析器的优先级, order 值越小优先级越高
     *
     * @return resolver 视图跳转
     */
    @Bean
    @ConditionalOnMissingBean
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(10);
        return resolver;
    }

    @Bean
    @ConditionalOnMissingBean
    public ExcelTemplate excelTemplate() {

        doPropertiesCheck(easyPoiProperties);

        return new ExcelTemplateImpl(easyPoiProperties.getImportExcelStorePath());
    }

    /**
     * 检查配置项
     * @param easyPoiProperties 配置项
     */
    private void doPropertiesCheck(EasyPoiProperties easyPoiProperties) {
        // 导入excel时数据的存储路径
        String importExcelStorePath = easyPoiProperties.getImportExcelStorePath();

        if (StringUtils.isEmpty(importExcelStorePath)) {
            throw new IllegalArgumentException("请设置easy-poi的存储路径, 配置项为 ruyuan.easy.poi.import-excel-store-path");
        }

        final File dir = new File(importExcelStorePath);
        // 如果指定的RocksDB的存储目录不存在,则进行创建
        if (!dir.exists()) {
            boolean mkdirsResult = dir.mkdirs();
            if (!mkdirsResult) {
                throw new IllegalStateException("设置easy-poi的存储路径失败，创建easy-poi存储文件夹时失败: " + importExcelStorePath + " 请检查是否存在目录权限问题。");
            }
        }
        // 如果指定的路径是不是文件夹，而是文件
        if (!dir.isDirectory()) {
            throw new IllegalStateException("设置easy-poi的存储路径失败，请指定文件夹而非文件: " + importExcelStorePath);
        }

    }

}

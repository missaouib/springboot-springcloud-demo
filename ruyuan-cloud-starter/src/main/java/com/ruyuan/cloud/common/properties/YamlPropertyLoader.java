package com.ruyuan.cloud.common.properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.Nullable;

import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * 加载yml格式的自定义配置文件
 *
 * @author zhonghuashishan
 */
public class YamlPropertyLoader implements PropertySourceFactory {

	@Override
	public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws FileNotFoundException {
		Properties propertiesFromYaml = loadYaml2Properties(resource);
		String sourceName = name != null ? name : resource.getResource().getFilename();
		assert sourceName != null;
		return new PropertiesPropertySource(sourceName, propertiesFromYaml);
	}

	/**
	 * 加载yaml文件并将yaml文件变为properties对象
	 * @param resource yaml资源文件对象
	 * @return properties
	 * @throws FileNotFoundException 文件未找到异常
	 */
	private Properties loadYaml2Properties(EncodedResource resource) throws FileNotFoundException {
		try {
			YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
			factory.setResources(resource.getResource());
			factory.afterPropertiesSet();
			return factory.getObject();
		} catch (IllegalStateException e) {
			Throwable cause = e.getCause();
			if (cause instanceof FileNotFoundException) {
				throw (FileNotFoundException) e.getCause();
			}
			throw e;
		}
	}
}

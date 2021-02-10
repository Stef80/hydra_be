package net.agm.hydra.config;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class FileUploadConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
		if (converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter) {
		ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
		mapper.registerModule(new Hibernate5Module());
		// replace Hibernate4Module() with the proper class for your hibernate version.
		}
		}
		}
}

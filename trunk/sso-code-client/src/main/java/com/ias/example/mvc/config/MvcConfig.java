package com.ias.example.mvc.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
@ComponentScan(basePackages = { "com.ias.example.mvc.web"})
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.valueOf("text/json;charset=UTF-8"));
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		converter.setSupportedMediaTypes(mediaTypes);
		converters.add(converter);
		super.configureMessageConverters(converters);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("login").setStatusCode(HttpStatus.OK);
		registry.addViewController("/").setStatusCode(HttpStatus.OK);
		super.addViewControllers(registry);
	}
}
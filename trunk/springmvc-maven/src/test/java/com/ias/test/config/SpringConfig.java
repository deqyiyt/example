package com.ias.test.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

//说明类为IoC容器  
@Configuration  
//指定自动扫描包  
@ComponentScan(basePackages = {"com.ias.test"})
//配置文件  
@PropertySource(value = {"classpath:application.properties"}) 
public class SpringConfig {
	
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(new String[]{"org/springframework/security/messages"});
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

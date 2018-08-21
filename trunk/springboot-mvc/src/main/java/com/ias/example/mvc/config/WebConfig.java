/**
 * 
 */
package com.ias.example.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableScheduling
public class WebConfig extends WebMvcConfigurerAdapter {
    
    /**
     * @Title 增加spring对{@value}的支持
     * @Method placeHolderConfigurer方法.<br>
     * @author jiuzhou.hu
     * @date 2016年7月28日 上午10:06:57
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
    	PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
    	c.setIgnoreUnresolvablePlaceholders(true);
    	return c;
    }
}

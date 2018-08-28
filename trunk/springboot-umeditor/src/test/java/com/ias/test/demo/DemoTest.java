package com.ias.test.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ias.test.config.SpringConfig;

//指定测试类的运行者  
@RunWith(SpringJUnit4ClassRunner.class)  
//指定spring配置类  
@ContextConfiguration(classes = {SpringConfig.class})  
public class DemoTest {

	@Value("${ias.test}")
	private String test;
	
	@Test
	public void test() {
		System.out.println(test);
	}
}

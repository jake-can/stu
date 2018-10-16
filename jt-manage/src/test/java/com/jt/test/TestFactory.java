package com.jt.test;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactory {
	
	@Test
	public void test01(){
		ApplicationContext app = new ClassPathXmlApplicationContext("spring/factory.xml");
		Calendar calendar = (Calendar) app.getBean("calendar1");
		System.out.println("获取当前时间:"+calendar.getTime());
	}
	
	@Test
	public void test02(){
		ApplicationContext app = new ClassPathXmlApplicationContext("spring/factory.xml");
		Calendar calendar = (Calendar) app.getBean("calendar2");
		System.out.println("获取当前时间:"+calendar.getTime());
	}
	
	@Test
	public void test03(){
		ApplicationContext app = new ClassPathXmlApplicationContext("spring/factory.xml");
		Calendar calendar = (Calendar) app.getBean("calendar3");
		System.out.println("获取当前时间:"+calendar.getTime());
	}
	

}

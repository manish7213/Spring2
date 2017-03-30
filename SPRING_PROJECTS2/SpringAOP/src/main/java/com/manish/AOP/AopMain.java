package com.manish.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.manish.AOP.service.ShapeService;

public class AopMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("com/manish/AOP/spring.xml");
		
		ShapeService shapeService=(ShapeService)ctx.getBean("shapeService");
		
		shapeService.getTriangle().setName("myTriangle");;

		System.out.println(shapeService.getTriangle().getName());

	}

}

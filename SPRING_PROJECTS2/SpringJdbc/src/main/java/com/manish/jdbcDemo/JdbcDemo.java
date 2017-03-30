package com.manish.jdbcDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manish.jdbcDemo.dao.JdbcDaoImpl;
import com.manish.jdbcDemo.dao.JdbcTemplateDaoSupportImpl;
import com.manish.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {

		//Circle circle=new jdbcDaoImpl().getCircleName(1);
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("com/manish/jdbcDemo/spring.xml");
		//JdbcDaoImpl dao=ctx.getBean("jdbcDaoImpl",JdbcDaoImpl.class);
		JdbcTemplateDaoSupportImpl dao=ctx.getBean("simpleJdbcDaoImpl",JdbcTemplateDaoSupportImpl.class);
		System.out.println(dao.getCircleCount());
		
	/*	Circle circle=dao.getCircleName(1);
		System.out.println(circle.getName());*/
		
		//System.out.println(dao.getCircleCount());
		
		//System.out.println(dao.getCircleNameusingTemplate(3));
		
		//System.out.println(dao.getCircleUsingMapper(2).getName());
		
		//dao.insertCircle(new Circle(6,"Sixth Circle"));
		
//		System.out.println(dao.getCircles().size());
//		
//		for(Circle c:dao.getCircles())
//			System.out.println(c.getId()+" "+c.getName());
		
		//dao.createTriangleTable();
		//dao.insertCircleUsingNamedParameterTemplate(new Circle(7,"Seventh Circle"));
		
		

	}

}

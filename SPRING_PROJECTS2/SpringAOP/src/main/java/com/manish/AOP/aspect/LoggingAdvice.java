package com.manish.AOP.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAdvice {
	
	@Before("allMethodsInTriangle() && logBeforeGetName() ")
	public void LoggingAdvice(JoinPoint joinPoint){
		
		System.out.println("Logging Advice before getName:"+joinPoint.getTarget());
	}
	
	@Before("args(name)")
	public void LoggingSecondAdvice(String name){
		
		System.out.println("the string passed is:"+name);
	}
	
	
	@Pointcut("execution(* getName(..))")
	public void logBeforeGetName(){}
	
	@Pointcut("execution(* com.manish.AOP.model.*.get*())")
	public void allGettersInModel(){}
	
/*	@Pointcut("execution(* com.manish.AOP.model.Triangle.*())")
	public void allMethodsInCircle(){}*/
	
	@Pointcut("within(com.manish.AOP.model.Triangle)")
	public void allMethodsInTriangle(){}
	
	

}

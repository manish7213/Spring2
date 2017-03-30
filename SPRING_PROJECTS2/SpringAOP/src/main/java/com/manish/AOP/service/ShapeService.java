package com.manish.AOP.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.manish.AOP.model.Circle;
import com.manish.AOP.model.Triangle;

public class ShapeService {

	private Circle circle;
	private Triangle triangle;

	public Circle getCircle() {
		return circle;
	}
	
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	public Triangle getTriangle() {
		return triangle;
	}
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}





}

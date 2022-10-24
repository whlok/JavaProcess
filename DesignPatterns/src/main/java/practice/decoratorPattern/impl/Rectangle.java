package practice.decoratorPattern.impl;

import practice.decoratorPattern.Shape;

/**
 * @author wenhoulai
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Rectangle");
	}
}

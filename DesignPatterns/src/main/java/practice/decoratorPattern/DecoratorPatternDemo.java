package practice.decoratorPattern;

import practice.decoratorPattern.impl.Rectangle;
import practice.decoratorPattern.impl.RedShapeDecorator;
import practice.decoratorPattern.impl.ShapeDecorator;

/**
 * @author wenhoulai
 */
public class DecoratorPatternDemo {

	public static void main(String[] args) {
		Shape circle = new Circle();
		ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
		ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

		System.out.println("Circle With normal border");
		circle.draw();

		System.out.println("\n Circle of red border");
		redCircle.draw();

		System.out.println("\n Rectangle of red border");
		redRectangle.draw();

	}
}

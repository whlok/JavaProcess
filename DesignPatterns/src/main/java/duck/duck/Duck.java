package duck.duck;

import duck.fly.FlyBehavior;
import duck.quack.QuackBehavior;

/**
 * @author wenhoulai
 */
public abstract class Duck {

	public QuackBehavior quackBehavior;
	public FlyBehavior flyBehavior;

	public void setQuackBehavior(QuackBehavior qb) {
		quackBehavior = qb;
	}

	public void setFlyBehavior(FlyBehavior fb) {
		flyBehavior = fb;
	}

	public void swim() {
	}

	public void display() {
	}

	public void performQuack() {
		quackBehavior.quack();
	}

	public void performFly() {
		flyBehavior.fly();
	}

	// 鸭子的其他方法
}

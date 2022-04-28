package duck.duck;


import duck.fly.FlyRocketPowered;
import duck.quack.MuteQuack;

/**
 * @author wenhoulai
 */
public class MallardDuck extends Duck {
	public MallardDuck() {
		flyBehavior = new FlyRocketPowered();
		quackBehavior = new MuteQuack();
	}

	@Override
	public void display() {
		System.out.println("I'm a mallard duck!");
	}

	@Override
	public void performFly() {
		super.performFly();
	}

	@Override
	public void performQuack() {
		super.performQuack();
	}
}

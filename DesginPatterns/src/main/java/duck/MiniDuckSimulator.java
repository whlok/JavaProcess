package duck;

import duck.duck.Duck;
import duck.duck.MallardDuck;
import duck.duck.ModelDuck;
import duck.fly.FlyRocketPowered;

/**
 * @author wenhoulai
 */
public class MiniDuckSimulator {
	public static void main(String[] args) {
		Duck mallard = new MallardDuck();
		mallard.performFly();
		mallard.performQuack();

		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
	}
}

package duck.fly;

/**
 * @author wenhoulai
 */
public class FlyNoWay implements FlyBehavior{

	@Override
	public void fly() {
		System.out.println("fly no way");
	}
}

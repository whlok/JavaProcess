package duck.quack;

/**
 * @author wenhoulai
 */
public class Squeak implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("Squeak");
	}
}

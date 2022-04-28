package duck.quack;

/**
 * @author wenhoulai
 */
public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Quack");
	}
}

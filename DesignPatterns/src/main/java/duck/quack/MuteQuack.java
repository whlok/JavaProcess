package duck.quack;

/**
 * @author wenhoulai
 */
public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("<< Silence >>");
	}
}

package chapter1;

/**
 * @author wenhoulai
 */
public class FinishEventListenerImpl implements FinishEventListener {

	@Override
	public void onEvent() {
		System.out.println("FinishEventListenerImpl");
	}
}

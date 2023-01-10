package thread;

/**
 * @author wenhoulai
 */
public class ThreadLearn {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});

		thread.start();
	}
}

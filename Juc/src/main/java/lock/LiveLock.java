package lock;

/**
 * 活锁。
 *
 * @author wenhoulai
 */
public class LiveLock {
	static volatile int count = 10;
	static final Object lock = new Object();

	public static void main(String[] args) {
		new Thread(() -> {

			while (count > 0) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				count--;
				System.out.println("线程1 count:" + count);
			}
		}, "t1").start();

		new Thread(() -> {
			while (count < 20) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				count++;
				System.out.println("线程2 count:" + count);
			}
		}, "t2").start();
	}
}

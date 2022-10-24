package lock;

/**
 * 死锁。
 * <p>
 * * 互斥
 * * 不可剥夺
 * * 请求和保持
 * * 循环等待
 *
 * @author wenhoulai
 */
public class DeadLock {
	public static Object resource1 = new Object();
	public static Object resource2 = new Object();

	public static void main(String[] args) {
		new Thread(() -> {
			synchronized (resource1) {
				System.out.println("线程1已经占用了资源1，开始请求资源2");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				synchronized (resource2) {
					System.out.println("线程1已经占用了资源2");
				}
			}
		}).start();

		new Thread(() -> {
			synchronized (resource2) {
				System.out.println("线程2已经占用了资源2，开始请求资源1");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				synchronized (resource1) {
					System.out.println("线程2已经占用了资源1");
				}
			}
		}).start();
	}
}

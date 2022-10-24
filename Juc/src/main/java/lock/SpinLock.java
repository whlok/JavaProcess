package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁。
 *
 * @author wenhoulai
 */
public class SpinLock {
	// 泛型装的是Thread，原子引用线程
	AtomicReference<Thread> atomicReference = new AtomicReference<>();

	public void lock() throws InterruptedException {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + "come in");
		//开始自旋，期望值为null，更新值是当前线程
		while (!atomicReference.compareAndSet(null, thread)) {
			Thread.sleep(1000);
			System.out.println(thread.getName() + " 正在自旋");
		}
		System.out.println(thread.getName() + "自旋成功");
	}

	public void unlock() {
		Thread thread = Thread.currentThread();
		//线程使用完锁把引用变为null
		atomicReference.compareAndSet(thread, null);
		System.out.println(thread.getName() + " invoke unlock");
	}

	public static void main(String[] args) throws InterruptedException {
		SpinLock lock = new SpinLock();

		new Thread(() -> {
			try {
				//占有锁
				lock.lock();
				Thread.sleep(3000);
				//释放锁
				lock.unlock();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}, "t1").start();

		// 让main线程暂停1秒，使得t1线程，先执行
		Thread.sleep(1000);

		new Thread(() -> {
			try {
				lock.lock();
				lock.unlock();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}, "t2").start();
	}
}

package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 异步模式之生产者/消费者：传统版。
 *
 * @author wenhoulai
 */
public class TraditionalProducerConsumer {
	public static void main(String[] args) {
		SharedData sharedData = new SharedData();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					sharedData.increment();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "t1").start();

		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					sharedData.decrement();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "t2").start();
	}
}

class SharedData {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() throws InterruptedException {
		// 同步代码块，加锁
		lock.lock();
		try {
			// 判断  防止虚假唤醒
			while (number != 0) {
				// 等待不能生产
				condition.await();
			}
			// 干活
			number++;
			System.out.println(Thread.currentThread().getName() + "\t " + number);
			// 通知 唤醒
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void decrement() throws InterruptedException {
		// 同步代码块，加锁
		lock.lock();
		try {
			// 判断 防止虚假唤醒
			while (number == 0) {
				// 等待不能消费
				condition.await();
			}
			// 干活
			number--;
			System.out.println(Thread.currentThread().getName() + "\t " + number);
			// 通知 唤醒
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

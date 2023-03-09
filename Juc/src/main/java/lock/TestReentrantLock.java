package lock;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @autor wenhoulai
 * Created on 2023-03-03
 */
@Slf4j
public class TestReentrantLock {
	private static final Logger logger = LoggerFactory.getLogger(TestReentrantLock.class);
	private static final TestReentrantLock instance = new TestReentrantLock();
//	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private volatile int count;

	private TestReentrantLock() {
	}

	public static TestReentrantLock getInstance() {
		return instance;
	}

	public int getCount() {
//		lock.readLock().lock();
		try {
			logger.info("count: ",count);
			System.out.println("count: "+count);
			return count;
		} finally {
//			lock.readLock().unlock();
		}
	}

	public synchronized void increment() {
//		lock.writeLock().lock();
		try {
			count++;
			logger.info("count: ",count);
			System.out.println("count: "+count);
		} finally {
//			lock.writeLock().unlock();
		}
	}

	public synchronized void decrement() {
//		lock.writeLock().lock();
		try {
			count--;
			logger.info("count: ",count);
			System.out.println("count: "+count);
		} finally {
//			lock.writeLock().unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		// 创建一个打印奇数的线程
		Thread thread1 = new Thread(() -> {
			for (int i = 1; i <= 1000000; i ++) {
				TestReentrantLock.getInstance().increment();
			}
		});

		// 创建一个打印偶数的线程
		Thread thread2 = new Thread(() -> {
			for (int i = 1; i <= 1000000; i++) {
				TestReentrantLock.getInstance().decrement();
			}
		});

		// 启动线程
		thread1.start();
		thread2.start();

		// 等待线程执行完毕
		thread1.join();
		thread2.join();

		// 所有线程执行完毕后，程序结束
		System.out.println("All threads are done.");
	}
}


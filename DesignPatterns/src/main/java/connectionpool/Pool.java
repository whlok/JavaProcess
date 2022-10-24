package connectionpool;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 连接池，享元模式。
 *
 * @author wenhoulai
 */
public class Pool {
	// 连接池大小
	private final int poolSize;

	// 连接对象的数组
	private Connection[] connections;

	// 连接状态数组 0表示空闲  1表示繁忙
	private AtomicIntegerArray states;

	public Pool(int poolSize) {
		this.poolSize = poolSize;
		this.connections = new Connection[poolSize];
		this.states = new AtomicIntegerArray(new int[poolSize]);
		for (int i = 0; i < poolSize; i++) {
			connections[i] = new MockConnection("连接" + (i + 1));
		}
	}

	/**
	 * 使用连接
	 *
	 * @return
	 */
	public Connection borrow() {
		while (true) {
			for (int i = 0; i < poolSize; i++) {
				if (states.get(i) == 0) {
					if (states.compareAndSet(i, 0, 1)) {
						System.out.println(Thread.currentThread().getName() + " borrow " + connections[i]);
						return connections[i];
					}
				}
			}
			synchronized (this) {
				try {
					System.out.println(Thread.currentThread().getName() + " wait...");
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 归还连接
	 *
	 * @param con
	 */
	public void free(Connection con) {
		for (int i = 0; i < poolSize; i++) {
			if (connections[i] == con) {
				states.set(i, 0);
				synchronized (this) {
					System.out.println(Thread.currentThread().getName() + " free " + connections[i]);
					this.notifyAll();
				}
				break;
			}
		}
	}
}

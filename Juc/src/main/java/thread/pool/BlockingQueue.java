package thread.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列。
 *
 * @author wenhoulai
 */
public class BlockingQueue<T> {
	// 1.任务队列
	private Deque<T> queue = new ArrayDeque<>();

	// 2.锁
	private ReentrantLock lock = new ReentrantLock();

	// 3.生产者条件变量
	private Condition fullWaitSet = lock.newCondition();

	// 4.消费者条件变量
	private Condition emptyWaitSet = lock.newCondition();

	// 5.容量
	private int capacity;

	public BlockingQueue(int capacity) {
		this.capacity = capacity;
	}

	// 带超时阻塞获取
	public T poll(long timeout, TimeUnit unit) {
		lock.lock();
		try {
			// 将时间统一转换成纳秒
			long nanos = unit.toNanos(timeout);
			while (queue.isEmpty()) {
				try {
					// 避免虚假唤醒后导致的重新等待单位时间，只需要等待剩余的时间
					if (nanos <= 0) {
						return null;
					}
					nanos = emptyWaitSet.awaitNanos(nanos);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			T t = queue.removeFirst();
			fullWaitSet.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}

	// 阻塞获取
	public T take() {
		lock.lock();
		try {
			while (queue.isEmpty()) {
				try {
					emptyWaitSet.await();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			T t = queue.removeFirst();
			fullWaitSet.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}

	// 带超时的阻塞添加
	public boolean offer(T task, long timeout, TimeUnit timeUnit) {
		lock.lock();
		try {
			long nanos = timeUnit.toNanos(timeout);
			while (queue.size() == capacity) {
				try {
					if (nanos <= 0) {
						System.out.println("放弃加入任务队列：" + task);
						return false;
					}
					System.out.println("等待加入任务队列：" + task);
					nanos = fullWaitSet.awaitNanos(nanos);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			queue.addLast(task);
			emptyWaitSet.signal();
		} finally {
			lock.unlock();
		}
		return true;
	}

	// 阻塞添加
	public void put(T task) {
		lock.lock();
		try {
			while (queue.size() == capacity) {
				try {
					fullWaitSet.await();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			queue.addLast(task);
			emptyWaitSet.signal();
		} finally {
			lock.unlock();
		}
	}

	// 获取大小
	private int size() {
		lock.lock();
		try {
			return capacity;
		} finally {
			lock.unlock();
		}

	}

	public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
		lock.lock();
		try {
			if (queue.size() == capacity) {
				rejectPolicy.reject(this, task);
			} else {
				queue.addLast(task);
				emptyWaitSet.signal();
			}
		} finally {
			lock.unlock();
		}
	}
}

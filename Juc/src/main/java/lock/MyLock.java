package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 不可重入锁。
 *
 * @author wenhoulai
 */
public class MyLock implements Lock {
	// 独占锁，不可重入
	class MySync extends AbstractQueuedLongSynchronizer {
		@Override
		protected boolean tryAcquire(long arg) {
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(long arg) {
			setExclusiveOwnerThread(null);
			setState(0); // volatile修饰的变量放在后面，防止指令重排
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}

		public Condition newCondition() {
			return new ConditionObject();
		}
	}

	private MySync sync = new MySync();

	@Override
	public void lock() {
		// 加锁，不成功则进入等待队列中等待
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// 加锁，可打断
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		// 尝试加锁，尝试一次
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// 尝试加锁，带超时
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		// 解锁
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		// 条件变量
		return sync.newCondition();
	}
}

package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替输出:连续输出 5 次 abc.
 *
 * @author wenhoulai
 */
public class TestReentrantLock {
	public static void main(String[] args) throws InterruptedException {
		AwaitSignal awaitSignal = new AwaitSignal(5);
		Condition a = awaitSignal.newCondition();
		Condition b = awaitSignal.newCondition();
		Condition c = awaitSignal.newCondition();

		new Thread(() -> {
			try {
				awaitSignal.print("a", a, b);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();

		new Thread(() -> {
			try {
				awaitSignal.print("b", b, c);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();

		new Thread(() -> {
			try {
				awaitSignal.print("c", c, a);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();

		Thread.sleep(1000);
		awaitSignal.lock();
		a.signal();
		awaitSignal.unlock();
	}
}

class AwaitSignal extends ReentrantLock {
	private int loopNumber;

	public AwaitSignal(int loopNumber) {
		this.loopNumber = loopNumber;
	}

	//参数1：打印内容  参数二：条件变量  参数二：唤醒下一个
	public void print(String str, Condition condition, Condition next) throws InterruptedException {
		for (int i = 0; i < loopNumber; i++) {
			lock();
			condition.await();
			System.out.println(str);
			next.signal();
			unlock();
		}
	}
}

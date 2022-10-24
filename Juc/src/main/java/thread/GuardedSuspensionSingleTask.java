package thread;

import java.util.Arrays;

/**
 * 同步模式之保护性暂停。
 *
 * @author wenhoulai
 */
public class GuardedSuspensionSingleTask {
	public static void main(String[] args) throws InterruptedException {
		GuardedObject object = new GuardedObject();

		new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			object.complete(Arrays.asList("a", "b", "c"));
		}).start();

		Object response = object.get(2500);
		if (response != null) {
			System.out.println("get response: " + response);
		} else {
			System.out.println("can't get response");
		}
	}
}

class GuardedObject {
	private Object response;
	private final Object lock = new Object();

	//获取结果
	//mills :最大等待时间
	public Object get(long mills) throws InterruptedException {
		synchronized (lock) {

			// 1) 记录最初时间
			long begin = System.currentTimeMillis();
			// 2) 已经经历的时间
			long timePassed = 0;

			while (response == null) {
				// 4) 假设 millis 是 1000，结果在 400 时唤醒了，那么还有 600 要等
				long waitTime = mills - timePassed;
				System.out.println("waitTime: " + waitTime);
				//经历时间超过最大等待时间退出循环
				if (waitTime <= 0) {
					System.out.println("break");
					break;
				}
				lock.wait(waitTime);

				// 3) 如果提前被唤醒，这时已经经历的时间假设为 400
				timePassed = System.currentTimeMillis() - begin;
				System.out.println("timePassed:" + timePassed + " object is " + response);
			}
			return response;
		}
	}

	//产生结果
	public void complete(Object response) {
		synchronized (lock) {
			// 条件满足，通知等待线程
			this.response = response;
			System.out.println("notify...");
			lock.notifyAll();
		}
	}
}
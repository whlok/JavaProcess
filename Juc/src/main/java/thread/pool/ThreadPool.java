package thread.pool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池。
 *
 * @author wenhoulai
 */
public class ThreadPool {
	// 任务队列
	private BlockingQueue<Runnable> taskQueue;

	// 线程集合
	private HashSet<Worker> workers = new HashSet<>();

	// 核心线程数
	private int coreSize;

	// 获取任务的超时时间
	private long timeout;

	private TimeUnit timeUnit;

	private RejectPolicy<Runnable> rejectPolicy;

	public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapicity, RejectPolicy<Runnable> rejectPolicy) {
		this.coreSize = coreSize;
		this.timeout = timeout;
		this.timeUnit = timeUnit;
		this.taskQueue = new BlockingQueue<>(queueCapicity);
		this.rejectPolicy = rejectPolicy;
	}

	// 执行任务
	public void execute(Runnable task) {
		// 当任务没有超过核心线程数时，创建一个新的
		synchronized (workers) {
			if (workers.size() < coreSize) {
				Worker worker = new Worker(task);
				workers.add(worker);
				System.out.println("新增worker: " + worker + "task: " + task);
				worker.start();
			} else {
				// 当任务超过核心线程数时，加入任务队列
				System.out.println("加入任务队列: " + task);
				taskQueue.tryPut(rejectPolicy, task);
				// 1）死等
				// 2）带超时等待
				// 3）放弃任务执行
				// 4）让调用者抛出异常
				// 5）让调用者自己执行任务
			}
		}
	}

	public class Worker extends Thread {
		private Runnable task;

		public Worker(Runnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			// 执行任务
			// 1）当task不为空，执行任务

			// 2）当task执行完毕，从任务队列中获取并执行任务
//			while (task != null || (task = taskQueue.take()) != null) {
			while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {

				try {
					System.out.println("正在执行... " + task);
					task.run();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					task = null;
				}
			}
			synchronized (workers) {
				System.out.println("worker被移除... " + this);
				workers.remove(this);
			}
		}
	}
}

package thread.pool;

import java.util.concurrent.TimeUnit;

/**
 * @author wenhoulai
 */
public class TestPool {
	public static void main(String[] args) {
		ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1,
				(queue, task) -> {
					// 1）死等
//                  queue.put(task);
					// 2）带超时等待
//					queue.offer(task, 500, TimeUnit.MILLISECONDS);
					// 3）放弃任务执行
//					System.out.println("放弃任务： " + task);
					// 4）让调用者抛出异常
//					throw new RuntimeException("任务执行失败 " + task);
					// 5）让调用者自己执行任务
					task.run();
				});
		for (int i = 0; i < 3; i++) {
			int j = i;
			threadPool.execute(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("打印： " + j);
			});
		}
	}
}

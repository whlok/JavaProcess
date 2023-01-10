package zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 模拟 50 个线程使用重入排它锁 InterProcessMutex 同时争抢锁。
 *
 * @author wenhoulai
 */
public class InterprocessLock {
	public static void main(String[] args) {
		CuratorFramework zkClient = getZKClient();
		String lockPath = "/lock";
		InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
		for (int i = 0; i < 50; i++) {
			new Thread(new TestThread(i, lock)).start();
		}

	}

	static class TestThread implements Runnable {

		private Integer threadFlag;
		private InterProcessMutex lock;

		public TestThread(Integer threadFlag, InterProcessMutex lock) {
			this.threadFlag = threadFlag;
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				lock.acquire();
				System.out.println("第" + threadFlag + "线程获取到了锁");
				Thread.sleep(1000);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					lock.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static CuratorFramework getZKClient() {
		String zkServerAddress = "127.0.0.1:2181";
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
		CuratorFramework zkClient = CuratorFrameworkFactory.builder()
				.connectString(zkServerAddress)
				.sessionTimeoutMs(5000)
				.connectionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.build();
		zkClient.start();
		return zkClient;
	}
}

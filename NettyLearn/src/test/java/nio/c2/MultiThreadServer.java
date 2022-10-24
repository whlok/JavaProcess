package nio.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static nio.c1.ByteBufferUtil.debugAll;

/**
 * 多线程nio.
 *
 * @author wenhoulai
 */
@Slf4j
public class MultiThreadServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		Thread.currentThread().setName("boss");
		ssc.bind(new InetSocketAddress(8088));
		ssc.configureBlocking(false);
		Selector boss = Selector.open();
		ssc.register(boss, SelectionKey.OP_ACCEPT, null);

		// 创建worker,线程数的设置大小可以参考阿姆达尔定律
		Worker[] worker = new Worker[Runtime.getRuntime().availableProcessors()];
		for (int i = 0; i < worker.length; i++) {
			worker[i] = new Worker("woker-" + i);
		}

		AtomicInteger index = new AtomicInteger();
		while (true) {
			boss.select();
			Iterator<SelectionKey> iter = boss.selectedKeys().iterator();
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				iter.remove();
				if (key.isAcceptable()) {
					SocketChannel sc = ssc.accept();
					sc.configureBlocking(false);
					log.debug("connected ... {}", sc.getRemoteAddress());
					// 2.关联selector
					log.debug("before register ... {}", sc.getRemoteAddress());
					// round robin

					worker[index.getAndIncrement() % worker.length].register(sc);
					log.debug("after register ... {}", sc.getRemoteAddress());
				}
			}
		}
	}

	static class Worker implements Runnable {
		private Thread thread;
		private Selector selector;
		private String name;

		private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue();
		// 保证worker线程只会被初始化一次
		private volatile boolean isInit = false;

		public Worker(String name) {
			this.name = name;
		}

		public void register(SocketChannel sc) throws IOException {
			if (!isInit) {
				thread = new Thread(this, name);
				thread.start();
				selector = Selector.open();
				isInit = true;
			}
			// 方案1：
//			selector.wakeup();
//			sc.register(selector,  SelectionKey.OP_READ, null);

			// 方案2：向队列添加任务，但这个任务并没有立即执行
			queue.add(() -> {
				try {
					sc.register(selector, SelectionKey.OP_READ, null);

				} catch (ClosedChannelException e) {
					throw new RuntimeException(e);
				}
			});
			selector.wakeup(); // 唤醒select方法
		}

		@Override
		public void run() {
			while (true) {
				try {
					selector.select();
					Runnable task = queue.poll();
					if (task != null) {
						task.run();
					}
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						iter.remove();
						if (key.isReadable()) {
							ByteBuffer buffer = ByteBuffer.allocate(16);
							SocketChannel channel = (SocketChannel) key.channel();
							log.debug("read ... {}", channel.getRemoteAddress());
							channel.read(buffer);
							buffer.flip();
							debugAll(buffer);
						}
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			}
		}
	}
}

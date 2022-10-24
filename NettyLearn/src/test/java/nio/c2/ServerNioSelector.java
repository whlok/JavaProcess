package nio.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static nio.c1.ByteBufferUtil.debugAll;

/**
 * @author wenhoulai
 */
@Slf4j
public class ServerNioSelector {

	public static void main(String[] args) throws IOException {
		// 使用NIO & Selector

		// 1. 创建selector,管理多个channel
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);

		// 2. 建立selector和channel的联系（注册）
		// SelectionKey 就是将来事件发生后，通过它可以知道事件和那个channel的事件
		SelectionKey sscKey = ssc.register(selector, 0, null);
		//key: 设置需要关注的事件，假如 只关注accpet事件
		sscKey.interestOps(SelectionKey.OP_ACCEPT);
		log.debug("register key: {}", sscKey);

		ssc.bind(new InetSocketAddress(8088));

		while (true) {
			// 3. select方法，没有事件发生，线程阻塞，有事件，线程才会恢复运行
			// select 在事件未处理时，它不会阻塞，事件发生后要么处理，要么取消，不能置之不理
			selector.select();

			// 4. 处理事件，selectorKeys 内部包含来所有发生的事件
			Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				// 处理key时，要从selectedKeys删除，否则下次处理会有问题
				iter.remove();

				log.debug("key: {}", key);
				// 区分事件类型，第一次来accept后先注册，注册完后收发数据
				if (key.isAcceptable()) {
					ServerSocketChannel channel = (ServerSocketChannel) key.channel();
					SocketChannel sc = channel.accept();
					sc.configureBlocking(false);
					ByteBuffer byteBuffer = ByteBuffer.allocate(16);
					// 把byteBuffer当作附件关联到selectionKey上
					SelectionKey scKey = sc.register(selector, 0, byteBuffer);
					scKey.interestOps(SelectionKey.OP_READ);
					log.debug("sc {}", sc);
					log.debug("scKey {}", scKey);
				} else if (key.isReadable()) {
					try {
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer) key.attachment();
						int read = channel.read(buffer);
						// 正常断开，read返回的是-1
						if (read == -1) {
							key.cancel();
						} else {
							// 处理消息的边界
							split(buffer);
							if (buffer.position() == buffer.limit()) {
								ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
								buffer.flip();
								newBuffer.put(buffer);
								// Todo:是否可以动态扩缩容
								key.attach(newBuffer);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						key.cancel();// 因为客户端断开了，因此需要将key取消，（从selector的keys集合中真正删除key）
					}
				}

//				 key.cancel();
			}
		}
	}

	private static void split(ByteBuffer source) {
		source.flip();

		for (int i = 0; i < source.limit(); i++) {
			if (source.get(i) == '\n') {
				int length = i + 1 - source.position();
				//完整消息存入新bytebuffer
				ByteBuffer target = ByteBuffer.allocate(length);
				// 从source读，向target写
				for (int j = 0; j < length; j++) {
					target.put(source.get());
				}
				debugAll(target);

			}

		}
		source.compact();
	}
}

package nio.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static nio.c1.ByteBufferUtil.debugRead;

/**
 * @author wenhoulai
 */
@Slf4j
public class ServerNio {
	public static void main(String[] args) throws IOException {
		// 使用NIO 来理解非阻塞，单线程

		// 0.ByteBuffer
		ByteBuffer buffer = ByteBuffer.allocate(16);

		// 1.创建服务器
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false); // 非阻塞模式

		// 2.绑定监听端口
		ssc.bind(new InetSocketAddress(8088));

		// 3. 连接集合
		List<SocketChannel> channels = new ArrayList<>();
		while (true) {

			// 4.accept 建立链接, SocketChannel 用来与客户端之间通信
			SocketChannel sc = ssc.accept(); // 非阻塞方法，线程还运行，如果没有建立连接，但sc是null

			if (sc != null) {
				log.debug("connected...{}", sc);
				sc.configureBlocking(false);  // 非阻塞模式
				channels.add(sc);
			}
			// 5.接收客户端发送的数据
			for (SocketChannel channel : channels) {

				int len = channel.read(buffer);// 非阻塞方法，线程仍运行，如果没有数据，read返回0
				if (len > 0) {
					buffer.flip();
					debugRead(buffer);
					buffer.clear();
					log.debug("after read...{}", sc);
				}
			}

		}
	}
}

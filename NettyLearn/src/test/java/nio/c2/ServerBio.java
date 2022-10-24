package nio.c2;

import lombok.extern.slf4j.Slf4j;
import nio.c1.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用NIO 来理解阻塞模式, 单线程处理。
 *
 * @author wenhoulai
 */
@Slf4j
public class ServerBio {
	public static void main(String[] args) throws IOException {
		// 使用NIO 来理解阻塞模式, 单线程处理

		// 0.ByteBuffer
		ByteBuffer buffer = ByteBuffer.allocate(16);

		// 1.创建子服务器
		ServerSocketChannel ssc = ServerSocketChannel.open();

		// 2.绑定监听端口
		ssc.bind(new InetSocketAddress(8088));

		// 3. 连接集合
		List<SocketChannel> channels = new ArrayList<>();
		while (true) {

			log.debug("connecting...");

			// 4.accept 建立链接, SocketChannel 用来与客户端之间通信
			SocketChannel sc = ssc.accept(); // 阻塞方法，线程停止运行
			log.debug("connected...{}", sc);

			channels.add(sc);

			// 5.接收客户端发送的数据
			for (SocketChannel channel : channels) {

				log.debug("before read...{}", sc);
				channel.read(buffer);        // 阻塞方法，线程停止运行
				buffer.flip();
				ByteBufferUtil.debugRead(buffer);
				buffer.clear();

				log.debug("after read...{}", sc);
			}

		}
	}
}

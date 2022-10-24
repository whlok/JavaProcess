package nio.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static nio.c1.ByteBufferUtil.debugAll;

/**
 * 异步文件读操作。
 *
 * @author wenhoulai
 */
@Slf4j
public class AioFileChannel {
	public static void main(String[] args) throws IOException {
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("data.txt"), StandardOpenOption.READ);
		// 参数1 Bytebuffer
		// 参数2 读取的起始位置
		// 参数3 attachment
		// 参数4 回调函数
		ByteBuffer buffer = ByteBuffer.allocate(16);
		log.debug("read begin...");
		channel.read(buffer, 0, buffer, new CompletionHandler<>() {
			@Override // read success
			public void completed(Integer result, ByteBuffer attachment) {
				log.debug("read success");
				attachment.flip();
				debugAll(attachment);
			}

			@Override // read failed
			public void failed(Throwable exc, ByteBuffer attachment) {
				log.debug("read failed");
			}
		});
		log.debug("read end...");
		// 保证主线程在读线程加载后还存活
		System.in.read();
	}
}

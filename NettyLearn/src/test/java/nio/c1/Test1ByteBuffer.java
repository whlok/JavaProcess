package nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wenhoulai
 */
@Slf4j
public class Test1ByteBuffer {
	public static void main(String[] args) {
		// FileChannel
		// 1. 输入输出流 2. RandomAccessFile
		try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
			// 准备缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(10);
			while (true) {
				// 从channel读取数据，向buffer写入
				int len = channel.read(buffer);
				log.debug("读到的字节数 {}", len);
				if (len == -1) {
					break;
				}
				// 打印buffer数据
				buffer.flip(); //切换至读模式
				while (buffer.hasRemaining()) {
					byte b = buffer.get();
					log.debug("字节 {}", (char) b);
				}
				// 切换写模式
				buffer.clear();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

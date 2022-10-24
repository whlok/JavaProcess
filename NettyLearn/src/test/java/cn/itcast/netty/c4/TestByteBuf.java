package cn.itcast.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * @author wenhoulai
 */
public class TestByteBuf {
	public static void main(String[] args) {
		ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
		log(buf);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			builder.append("a");
		}
		buf.writeBytes(builder.toString().getBytes());
		log(buf);
	}

	private static void log(ByteBuf byteBuf) {
		int len = byteBuf.readableBytes();
		int rows = len / 16 + (len % 15 == 0 ? 0 : 1) + 4;
		StringBuilder buf = new StringBuilder(rows * 80 * 2)
				.append("read index: ").append(byteBuf.readerIndex())
				.append(", write index: ").append(byteBuf.writerIndex())
				.append(", capacity:").append(byteBuf.capacity())
				.append(NEWLINE);
		appendPrettyHexDump(buf, byteBuf);
		System.out.println(buf.toString());
	}
}

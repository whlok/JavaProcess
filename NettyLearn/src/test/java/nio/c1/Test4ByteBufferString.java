package nio.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 字符串和ByteBuffer的转换。
 *
 * @author wenhoulai
 */
public class Test4ByteBufferString {
	public static void main(String[] args) {
		//1. 字符串转为ByteBuffer
		ByteBuffer buffer1 = ByteBuffer.allocate(16);
		buffer1.put("hello".getBytes());
		ByteBufferUtil.debugAll(buffer1);

		//2.Charset: 自动切换读模式
		ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
		ByteBufferUtil.debugAll(buffer2);

		//3.wrap:自动切换读模式
		ByteBuffer byteBuffer3 = ByteBuffer.wrap("hello".getBytes());
		ByteBufferUtil.debugAll(byteBuffer3);

		//4.转为字符串
		String str2 = StandardCharsets.UTF_8.decode(buffer2).toString();
		System.out.println(str2);

		// 切换为读模式
		buffer1.flip();
		String str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
		System.out.println(str1);
	}
}

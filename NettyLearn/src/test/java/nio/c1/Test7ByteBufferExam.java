package nio.c1;

import java.nio.ByteBuffer;

import static nio.c1.ByteBufferUtil.debugAll;

/**
 * 粘包，半包。
 *
 * @author wenhoulai
 */
public class Test7ByteBufferExam {
	public static void main(String[] args) {
		/*
			网络有多条数据发送给服务端，数据之间使用\n进行分隔
			但由于某种原因这些数据在接收时，被进行了重新组合例。例如原始数据有3条
				Hello,world\n
				I'm Zhangsan\n
				How are you?\n
			变成了下面俩个bytebuffer()   ---（粘包，半包）
				Hello,world\nI'm Zhangsan\nHo
				w are you?\n
			现在要求编写程序，将错乱的数据恢复到原始按\n分隔的数据
		 */
		ByteBuffer source = ByteBuffer.allocate(32);
		source.put("Hello,world\nI'm Zhangsan\nHo".getBytes());
		split(source);
		source.put("w are you?\n".getBytes());
		split(source);
		// 如果一直没有换行符。。。
	}

	private static void split(ByteBuffer source) {
		source.flip();

		for (int i = 0; i < source.limit(); i++) {
			if (source.get(i) == '\n') {
				// 这里调用position()，是因为后续for写入target中调用了get()，会改变position
				// 这里加1是为了写入换行符
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

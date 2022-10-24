package nio.c1;

import java.nio.ByteBuffer;

import static nio.c1.ByteBufferUtil.debugAll;

/**
 * @author wenhoulai
 */
public class Test2ByteBufferRead {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put(new byte[]{'a', 'b', 'c', 'd'});
		debugAll(buffer);
		buffer.flip();

		// rewind 从头开始读,让position位置变成0，重新读还可以直接通过数组下标 buffer.get(int i)
		buffer.get(new byte[4]);
		buffer.rewind();
//		System.out.println((char) buffer.get());

		// mark&reset
		// mark做一个标记， 记录position位置，reset将position重置到mark的位置
		System.out.println((char) buffer.get());
		System.out.println((char) buffer.get());

		buffer.mark(); //在该position做标记
		System.out.println((char) buffer.get());
		System.out.println((char) buffer.get());

		buffer.reset(); // 将positon重制到mark的索引

		// get(i)
		System.out.println((char) buffer.get(2));
		debugAll(buffer);
	}
}

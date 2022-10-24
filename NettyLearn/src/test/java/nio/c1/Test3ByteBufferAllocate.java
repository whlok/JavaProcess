package nio.c1;

import java.nio.ByteBuffer;

/**
 * @author wenhoulai
 */
public class Test3ByteBufferAllocate {
	public static void main(String[] args) {
		/**
		 * class Java NIO.HeapByteBuffer -- Java 堆内存，读写效率低，受到GC的影响
		 * class Java NIO.DirectByteBuffer --直接内存<系统内存>，读写效率高<少一次拷贝>， 不会受到GC影响，分配的效率低，使用不当可能造成内存泄漏
		 */
		System.out.println(ByteBuffer.allocate(16).getClass());
		System.out.println(ByteBuffer.allocateDirect(16).getClass());

	}
}

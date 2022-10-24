package nio.c1;

/**
 * @author wenhoulai
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 俩个文件数据传输。
 *
 * @author wenhoulai
 */
public class Test8FileChannelTransferTo {
	public static void main(String[] args) {
		try (FileChannel from = new FileInputStream("data.txt").getChannel();
		     FileChannel to = new FileOutputStream("to.txt").getChannel();) {
			// 效率高： 底层利 用操作系统的0拷贝优化；
			// 上限：2G 数据  -- 多次传输
			long size = from.size();
			// left： 还剩余多少字节
			for (long left = size; left > 0; ) {
				System.out.println("position:" + (size - left) + " left:" + left);
				left -= from.transferTo(size - left, left, to);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

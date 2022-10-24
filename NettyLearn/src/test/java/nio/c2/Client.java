package nio.c2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author wenhoulai
 */
public class Client {
	public static void main(String[] args) throws IOException {
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("localhost", 8088));
		sc.write(Charset.defaultCharset().encode("0123456789abcdefgh\n"));
		System.out.println("waiting...");
		System.in.read();
	}
}

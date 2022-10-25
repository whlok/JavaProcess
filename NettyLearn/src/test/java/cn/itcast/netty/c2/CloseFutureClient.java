package cn.itcast.netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author wenhoulai
 */
@Slf4j
public class CloseFutureClient {
	public static void main(String[] args) throws InterruptedException {
		NioEventLoopGroup group = new NioEventLoopGroup();
		ChannelFuture channelFuture = new Bootstrap()
				.group(group)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) {
						ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
						ch.pipeline().addLast(new StringEncoder());
					}
				})
				.connect(new InetSocketAddress("localhost", 8090));
		Channel channel = channelFuture.sync().channel();

		log.debug("{}", channel);
		new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				String line = scanner.nextLine();
				if ("q".equals(line)) {
					channel.close(); //  close异步操作
//					log.debug("close after");
					break;
				}
				channel.writeAndFlush(line);
			}
		}, "input").start();

		ChannelFuture closeFuture = channel.closeFuture();
		// 1.同步
//		System.out.println("waiting close");
//		closeFuture.sync();
//		log.debug("close after");

		// 2.异步
		closeFuture.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				log.debug("close after");
				group.shutdownGracefully();
			}
		});
	}
}

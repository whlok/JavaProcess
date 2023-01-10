package cn.itcast.netty.c5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 粘包现象。
 *
 * @author wenhoulai
 */
public class HelloServer2 {
	static final Logger log = LoggerFactory.getLogger(HelloServer2.class);

	public static void main(String[] args) {
		new HelloServer2().start();
	}

	void start() {
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup worker = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap()
					.channel(NioServerSocketChannel.class)
					// 调整系统的接收缓冲器（滑动窗口）
//					.option(ChannelOption.SO_RCVBUF, 10)
					// 调整netty的接收缓冲区（bytebuf）
//					.childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(16, 16, 16))
					.group(boss, worker)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) {
							ch.pipeline().addLast(new FixedLengthFrameDecoder(16));
							ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
						}
					});
			ChannelFuture channelFuture = serverBootstrap.bind(8090);
			log.debug("{} binding...", channelFuture.channel());
			channelFuture.sync();
			log.debug("{} bound...", channelFuture.channel());
			// 关闭channel
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			log.error("server error", e);
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
			log.debug("stopped");
		}
	}
}
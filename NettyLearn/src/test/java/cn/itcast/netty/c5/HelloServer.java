package cn.itcast.netty.c5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 粘包现象。
 *
 * @author wenhoulai
 */
public class HelloServer {
	static final Logger log = LoggerFactory.getLogger(HelloServer.class);

	void start() {
		NioEventLoopGroup boss = new NioEventLoopGroup(1);
		NioEventLoopGroup worker = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap()
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_RCVBUF, 3)
					.group(boss, worker)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) {
							ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
							ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
								@Override
								public void channelActive(ChannelHandlerContext ctx) throws Exception {
									// 连接建立时会执行该方法
									log.debug("connected {}", ctx.channel());
									super.channelActive(ctx);
								}

								@Override
								public void channelInactive(ChannelHandlerContext ctx) throws Exception {
									// 连接断开时会执行该方法
									log.debug("disconnect {}", ctx.channel());
									super.channelInactive(ctx);
								}
							});
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

	public static void main(String[] args) {
		new HelloServer().start();
	}
}
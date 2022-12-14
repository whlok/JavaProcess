package cn.itcast.netty.c5;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wenhoulai
 */
public class HelloClient {
	static final Logger log = LoggerFactory.getLogger(HelloClient.class);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			send();
		}
	}

	private static void send() {
		NioEventLoopGroup worker = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap()
					.channel(NioSocketChannel.class)
					.group(worker)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							log.debug("connected...");
							ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
								@Override
								public void channelActive(ChannelHandlerContext ctx) throws Exception {
									log.debug("sending...");
									// 每次发送16个字节的数据，共发送10次

									ByteBuf buffer = ctx.alloc().buffer(16);
									buffer.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
									ctx.writeAndFlush(buffer);
									// 使用短连接解决粘包
									ctx.channel().close();

								}
							});
						}
					});

			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();
			channelFuture.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			log.error("client error", e);
		} finally {
			worker.shutdownGracefully();
		}
	}
}
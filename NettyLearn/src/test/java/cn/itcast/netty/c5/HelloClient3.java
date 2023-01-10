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

import java.util.Random;

/**
 * 使用定长解码器。
 *
 * @author wenhoulai
 */
public class HelloClient3 {
	static final Logger log = LoggerFactory.getLogger(HelloClient3.class);

	public static void main(String[] args) {
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
									char c = 'a';
									log.debug("sending...");
									// 每次发送16个字节的数据，共发送10次
									for (int i = 0; i < 10; i++) {
										ByteBuf buffer = ctx.alloc().buffer(16);
										byte[] bytes = new byte[16];
										Random r = new Random();
										for (int j = 0; j < r.nextInt(16) + 1; j++) {
											bytes[i] = (byte) c;
										}
										buffer.writeBytes(bytes);
										c++;
										ctx.writeAndFlush(buffer);
									}
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
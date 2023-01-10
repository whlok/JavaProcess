package cn.itcast.netty.c6;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author wenhoulai
 */
public class RedisClient {
	static final Logger log = LoggerFactory.getLogger(RedisClient.class);

	public static void main(String[] args) {
		NioEventLoopGroup group = new NioEventLoopGroup();

		try {
			ChannelFuture channelFuture = new Bootstrap()
					.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
							ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
								@Override
								public void channelActive(ChannelHandlerContext ctx) throws Exception {
									final byte[] LINE = {'\r', '\n'};
									ByteBuf buffer = ctx.alloc().buffer();
									// 该指令一共有3部分，每条指令之后都要添加回车与换行符
									buffer.writeBytes("*3".getBytes());
									buffer.writeBytes(LINE);
									// 指令的长度是3
									buffer.writeBytes("$3".getBytes());
									buffer.writeBytes(LINE);
									// 指令是set指令
									buffer.writeBytes("set".getBytes());
									buffer.writeBytes(LINE);
									// 指令的长度是4
									buffer.writeBytes("$4".getBytes());
									buffer.writeBytes(LINE);
									buffer.writeBytes("name".getBytes());
									buffer.writeBytes(LINE);
									// 指令的长度是6
									buffer.writeBytes("$9".getBytes());
									buffer.writeBytes(LINE);
									buffer.writeBytes("wenhoulai".getBytes());
									buffer.writeBytes(LINE);
									super.channelActive(ctx);
								}
							});
						}
					})
					.connect(new InetSocketAddress("localhost", 8090)).sync();

			// 关闭channel
			channelFuture.channel().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}
}

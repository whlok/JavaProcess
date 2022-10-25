package cn.itcast.netty.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author wenhoulai
 */
@Slf4j
public class EventLoopServer {
	public static void main(String[] args) {
		// 细分2: 创建一个独立的EventLoop,解锁一个handler处理时间较长
		EventLoopGroup group = new DefaultEventLoop();
		new ServerBootstrap()
				// boss & worker
				// 细分1： boss只负责ServerSocketChannel 上accept事件， worker负责socketChannel上的读写
				.group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) {
						ch.pipeline().addLast("handler1", new ChannelInboundHandlerAdapter() {
							@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
								ByteBuf buf = (ByteBuf) msg;
								log.debug(buf.toString(Charset.defaultCharset()));
								ctx.fireChannelRead(msg); // 将消息传递给下一个handler
							}
						}).addLast(group, "handler2", new ChannelInboundHandlerAdapter() {
							@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
								ByteBuf buf = (ByteBuf) msg;
								log.debug(buf.toString(Charset.defaultCharset()));
							}
						});

					}
				})
				.bind(8090);
	}
}

package cn.itcast.netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author wenhoulai
 */
@Slf4j
public class EventLoopClient {
	public static void main(String[] args) throws InterruptedException {
		//1. 启动类
		ChannelFuture channelFuture = new Bootstrap()
				//2. 添加EventLoop
				.group(new NioEventLoopGroup())
				//3. 选择客户端channel 实现
				.channel(NioSocketChannel.class)
				//4. 添加处理器
				.handler(new ChannelInitializer<NioSocketChannel>() {
					@Override // 连接建立后被调用
					protected void initChannel(NioSocketChannel ch) throws Exception {
						ch.pipeline().addLast(new StringEncoder());
					}
				})
				//5. 连接到服务器, 异步非阻塞操作，真正执行connect的是Nio线程
				.connect(new InetSocketAddress("localhost", 8090));


		// 1.使用sync方法同步处理结果
//		channelFuture.sync();// 阻塞方法，直到连接建立后执行后续
//		Channel channel = channelFuture.channel();// 代表连接对象
//		channel.writeAndFlush("1");
//		System.out.println(channel);

		// 2.使用addListener(回调对象)异步获取结果
		channelFuture.addListener(new ChannelFutureListener() {
			@Override
			// 在nio线程连接建立好之后，会调用operationComplete
			public void operationComplete(ChannelFuture future) throws Exception {
				Channel channel = future.channel();
				channel.writeAndFlush("hello");
				log.debug("{}", channel);
			}
		});

		System.out.println("Ok");


	}
}

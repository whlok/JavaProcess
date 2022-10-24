package cn.itcast.netty.c2;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

/**
 * EventLoop.
 *
 * @author wenhoulai
 */
public class TestEventLoop {
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup(2); // io事件，普通任务，定时任务
//		EventLoopGroup defaultEventLoop = new DefaultEventLoop(); // 普通任务，定时任务
		// 通过next方法可以获得下一个EventLoop
		System.out.println(group.next());
		System.out.println(group.next());

		// 普通任务
		group.next().execute(() -> System.out.println(Thread.currentThread().getName()));

		// 定时任务
		group.next().scheduleAtFixedRate(() -> {
			System.out.println(Thread.currentThread().getName());
		}, 0, 1, TimeUnit.SECONDS);

		// 优雅关闭
		group.shutdownGracefully();
	}
}

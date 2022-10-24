package cn.itcast.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author wenhoulai
 */
@Slf4j
public class TestNettyPromise {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//1
		EventLoop eventLoop = new NioEventLoopGroup().next();

		//2
		DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

		new Thread(() -> {
			log.debug("开始计算。。。");
			try {
				Thread.sleep(1000);
				promise.setSuccess(90);
			} catch (Exception e) {
				e.printStackTrace();
				promise.setFailure(e);
			}
		}).start();

		log.debug("等待结果。。");
		log.debug("结果是 {}", promise.get());
	}
}

package cn.itcast.netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author wenhoulai
 */
public class HelloServer {
	public static void main(String[] args) {
		// 1. 启动器，负责组装netty组件，启动服务器
		new ServerBootstrap()
				// 2. BossEventLoop, WorkerEventLoop(selector, thread), group组
				// EventLoop可以理解为线程池+Selector
				.group(new NioEventLoopGroup())
				// 3. 选择服务器的ServerSocketChannel
				.channel(NioServerSocketChannel.class)
				// 4. child负责读写，该方法决定了child执行哪些操作
				.childHandler(
						// 5. channel代表和客户端进行数据读写的通道，Initializer初始化，负责添加别的handler
						// ChannelInitializer仅执行一次
						new ChannelInitializer<NioSocketChannel>() {
							// 连接建立后被调用
							@Override
							protected void initChannel(NioSocketChannel ch) throws Exception {
								// 6. 添加具体的handler
								ch.pipeline().addLast(new StringDecoder()); // 将ByteBuf 转为字符串
								ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义handler
									@Override // 读事件
									public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
										// 打印上一步转换好的字符串
										System.out.println(msg);
									}
								});
							}
						})
				// 7. 绑定监听端口
				.bind(8088);
	}
}


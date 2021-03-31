package com.silva.chetax.spring.life.test.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class EchoClient {
	public void connect(String ip, int port) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).option(ChannelOption.TCP_NODELAY, true).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 2, 4, 0, 2));
							ch.pipeline().addLast(new EchoClientHandler());
						}
					});

			ChannelFuture f = b.connect(ip, port).sync();
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8060;
		try {
			if (args != null && args.length > 0) {
				port = Integer.parseInt(args[0]);
			}
			new EchoClient().connect("127.0.0.1", port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

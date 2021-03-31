package com.silva.chetax.spring.life.test.demo.netty;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	private int counter = 0;
	static final String ECHO_REQ = "Hi,liujunliang. Welcome to Netty.@_";

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf body = (ByteBuf) msg;
//		CCMessageHeader recHd = new CCMessageHeader();
		int nLength = body.readInt();
		int nType = body.readInt();
		int nDataSize = body.readableBytes();
		byte[] aa = new byte[nDataSize];
		body.readBytes(aa);
		String myMsg = new String(aa, Charset.forName("utf-8"));

		System.out.println("this is " + ++counter + " times receive server:[" + myMsg + "]" + " Type[" + nType + "]");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		CCMessageHeader hd = new CCMessageHeader();
		hd.setType(1);
		for (int n = 0; n < 10; n++) {
			String strData = String.format("client data%d...", n + 1);
			hd.setData(strData);
			ByteBuf echo = Unpooled.directBuffer();
			echo.writeBytes(hd.getMessageFlag());
			echo.writeInt(hd.getLength());
			echo.writeInt(hd.getType());
			echo.writeCharSequence(hd.getData(), Charset.forName("utf-8"));
			ctx.write(echo);
		}
		ctx.flush();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}

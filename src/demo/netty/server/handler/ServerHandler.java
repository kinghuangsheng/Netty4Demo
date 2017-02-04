package demo.netty.server.handler;

import demo.netty.common.message.AbsMessage;
import demo.netty.common.message.TestMessage;
import demo.netty.common.util.LogUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
	
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleEvent = (IdleStateEvent) evt;
			if (idleEvent.state() == IdleState.READER_IDLE) {
			} else if (idleEvent.state() == IdleState.WRITER_IDLE) {
			} else if (idleEvent.state() == IdleState.ALL_IDLE) {
			}
			ctx.close();
		}

	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		LogUtil.info("ip已连接：" + ctx.channel().remoteAddress());
		final ChannelFuture f = ctx.writeAndFlush(new TestMessage());
//		f.addListener(new ChannelFutureListener() {
//			@Override
//			public void operationComplete(ChannelFuture future) {
//				assert f == future;
//				// ctx.close();
//			}
//		});
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		AbsMessage absMessage = (AbsMessage) msg;
		absMessage.handle(ctx);
		// ByteBuf in = (ByteBuf) msg;
		// in.release();
		// try {
		// while (in.isReadable()) {
		//// System.out.print((char) in.readByte());
		//// System.out.flush();
		// ctx.writeAndFlush(msg);
		// }
		// } finally {
		// ReferenceCountUtil.release(msg); 
		// }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
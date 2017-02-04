package demo.netty.common.message;

import demo.netty.common.annotation.AbsMessageImpl;
import io.netty.channel.ChannelHandlerContext;


@AbsMessageImpl
public class PingMessage extends AbsMessageV1{

	public PingMessage() {
		super(MSG_TYPE_PING);
	}

	@Override
	public void handle(ChannelHandlerContext ctx) {
		ctx.writeAndFlush(new PongMessage());
	}

}

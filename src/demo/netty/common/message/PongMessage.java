package demo.netty.common.message;

import demo.netty.common.annotation.AbsMessageImpl;
import io.netty.channel.ChannelHandlerContext;


@AbsMessageImpl
public class PongMessage extends AbsMessageV1{

	public PongMessage() {
		super(MSG_TYPE_PONG);
	}

	@Override
	public void handle(ChannelHandlerContext ctx) {
		
	}


}

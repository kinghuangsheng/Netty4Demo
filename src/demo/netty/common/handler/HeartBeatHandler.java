package demo.netty.common.handler;

import demo.netty.common.message.PingMessage;
import demo.netty.common.util.LogUtil;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatHandler extends ChannelDuplexHandler {
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

		if(evt instanceof IdleStateEvent){
			IdleStateEvent idleEvent = (IdleStateEvent) evt;
			if(idleEvent.state() == IdleState.READER_IDLE){
				LogUtil.info("client READER_IDLE");
			}else if(idleEvent.state() == IdleState.WRITER_IDLE){
				LogUtil.info("client WRITER_IDLE");
			}else if(idleEvent.state() == IdleState.ALL_IDLE){
				LogUtil.info("client ALL_IDLE");
			}
			ctx.writeAndFlush(new PingMessage());
		}
		
	}
	 
}

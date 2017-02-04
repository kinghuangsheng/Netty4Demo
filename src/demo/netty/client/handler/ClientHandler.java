package demo.netty.client.handler;

import demo.netty.common.message.AbsMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	
	
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	
    	AbsMessage absMessage = (AbsMessage) msg;
    	absMessage.handle(ctx);
    	
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
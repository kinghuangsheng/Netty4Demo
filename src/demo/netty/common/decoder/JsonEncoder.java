package demo.netty.common.decoder;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;

import demo.netty.common.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class JsonEncoder extends ChannelOutboundHandlerAdapter  { // (1)


	@Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
		try {
			String msgString = JSON.toJSONString(msg);
			LogUtil.info("发送：" + msgString);
			byte[] msgBytes = msgString.getBytes("utf-8");
			ByteBuf encoded = ctx.alloc().buffer(4 + msgBytes.length);
	        encoded.writeInt(msgBytes.length);
	        encoded.writeBytes(msgBytes);
	        ctx.write(encoded, promise); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        
    }
	
	
}
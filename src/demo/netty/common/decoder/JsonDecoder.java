package demo.netty.common.decoder;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import demo.netty.common.util.AbsMessageFindUtil;
import demo.netty.common.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class JsonDecoder extends ByteToMessageDecoder { // (1)
	
	private static HashMap<Integer, Class<?>> map;
	static {
		map =  AbsMessageFindUtil
				.findClassByAnnotationNameInPackageName("demo.netty");

	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
		if (in.readableBytes() < 4) {
			return;
		}

		in.markReaderIndex();
		int length = in.readInt();

		if (in.readableBytes() < length) {
			in.resetReaderIndex();
			return;
		}
		
		try {
			byte[] bytes = new byte[length];
			in.readBytes(bytes);
			String msg = new String(bytes, "utf-8");
			LogUtil.info("接收到：" + msg);
			JSONObject jsonObject = JSONObject.parseObject(msg);
			out.add(JSONObject.parseObject(msg, map.get(jsonObject.getInteger("type"))));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
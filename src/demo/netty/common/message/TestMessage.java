package demo.netty.common.message;

import demo.netty.common.annotation.AbsMessageImpl;
import demo.netty.common.util.LogUtil;
import io.netty.channel.ChannelHandlerContext;


@AbsMessageImpl

public class TestMessage extends AbsMessageV1 {
	
	private int id;
	private String name;
	
	public TestMessage() {
		super(MSG_TYPE_TEST);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void handle(ChannelHandlerContext ctx) {
		LogUtil.info("这是测试消息");
	}

}

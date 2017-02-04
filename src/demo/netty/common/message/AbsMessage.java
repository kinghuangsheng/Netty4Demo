package demo.netty.common.message;

import io.netty.channel.ChannelHandlerContext;

public abstract class AbsMessage {
	
	public final static int VERSION_V1 = 1;
	
	private int version;
	private int type;
	
	public void AbsMessageV1(){
		
	}
	

	public AbsMessage(int version, int type) {
		super();
		this.version = version;
		this.type = type;
	}


	public abstract void handle(ChannelHandlerContext ctx);


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}

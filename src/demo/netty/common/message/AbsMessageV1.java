package demo.netty.common.message;

public abstract class AbsMessageV1 extends AbsMessage{

	

	public final static int MSG_TYPE_PING = 1;
	public final static int MSG_TYPE_PONG = 2;
	public final static int MSG_TYPE_TEST = 3;
	
	public AbsMessageV1(int type) {
		super(VERSION_V1, type);
	}
	
	
}

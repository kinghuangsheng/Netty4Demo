package demo.netty.client;

public class NettyClientMain {
	
	
	public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = Integer.parseInt("8080");
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect(host, port);
        
    }
	
	
}

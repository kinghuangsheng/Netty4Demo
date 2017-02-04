package demo.netty.client;

import java.util.concurrent.TimeUnit;

import demo.netty.client.handler.ClientHandler;
import demo.netty.common.decoder.JsonDecoder;
import demo.netty.common.decoder.JsonEncoder;
import demo.netty.common.handler.HeartBeatHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClient {
	
	
	private Bootstrap bootstrap;
	private EventLoopGroup workerGroup;
    
	
	NettyClient(){
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		bootstrap = new Bootstrap(); // (1)
        bootstrap.group(workerGroup); // (2)
        bootstrap.channel(NioSocketChannel.class); // (3)
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true); // (4)
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new JsonEncoder(), new JsonDecoder(), new ClientHandler());
                ch.pipeline().addLast("timeout", new IdleStateHandler(10, 10, 20, TimeUnit.SECONDS));
                ch.pipeline().addLast("heartbeat", new HeartBeatHandler());
            }
        });
	}
    
	public void connect(String host, int port) throws Exception {
        try {
            ChannelFuture f = bootstrap.connect(host, port).sync(); // (5)
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}

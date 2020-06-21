package handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;
import util.UserDecoder;
import util.UserEncoder;

public class EchoChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler());
        pipeline.addLast(new UserDecoder());
        pipeline.addLast(new EchoOutHandler());
        pipeline.addLast(new UserEncoder());
        pipeline.addLast(new EchoServerPreHandler());
        pipeline.addLast(new EchoServerHandler());

    }
}

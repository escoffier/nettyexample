package services;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoService extends AbstractService {
    static final Logger logger = LoggerFactory.getLogger(EchoService.class);

//    public EchoService(int port, ChannelHandler... channelHandler) {
//        super(port, channelHandler);
//    }

    public EchoService(int port, ChannelInitializer<SocketChannel> channelChannelInitializer) {
        super(port, channelChannelInitializer);
    }

    @Override
    public void start() {
//        channelHandlers.add(new util.UserDecoder());
//        channelHandlers.add(new handlers.EchoOutHandler());
//        channelHandlers.add(new util.UserEncoder());
//        channelHandlers.add(new handlers.EchoServerPreHandler());
//        channelHandlers.add(new handlers.EchoServerHandler());
//        channelChannelInitializer = new handlers.EchoChannelInitializer();
        if (channelChannelInitializer == null) {
            throw new IllegalArgumentException("ChannelInitializer is null");
        }
        doStart();
    }
}

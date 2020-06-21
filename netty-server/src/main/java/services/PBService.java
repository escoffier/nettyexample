package services;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PBService extends AbstractService{
    static final Logger logger = LoggerFactory.getLogger(PBService.class);

//    public PBService(int port, ChannelHandler... channelHandlers) {
//        super(port, channelHandlers);
//    }
//
//    public PBService(int port, List<ChannelHandler> channelHandlers) {
//        super(port, channelHandlers);
//    }

    public PBService(int port, ChannelInitializer<SocketChannel> channelChannelInitializer) {
        super(port, channelChannelInitializer);
    }

    @Override
    public void start() {
//        // Decoders
//        channelHandlers.add(new ProtobufVarint32FrameDecoder());
//        channelHandlers.add(new ProtobufDecoder(CheckHealth.HealthCheckRequest.getDefaultInstance()));
//
//        channelHandlers.add(new handlers.PBOutHandler());
//
//        // Encoders
//        channelHandlers.add(new ProtobufVarint32LengthFieldPrepender());
//        channelHandlers.add(new ProtobufEncoder());
//
//        channelHandlers.add(new handlers.PBServerHandler());

//        channelChannelInitializer = new handlers.PBChannelInitializer();
        if (channelChannelInitializer == null) {
            throw new IllegalArgumentException("ChannelInitializer is null");
        }
        doStart();
    }
}

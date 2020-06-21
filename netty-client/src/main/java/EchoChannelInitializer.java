import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import util.UserDecoder;
import util.UserEncoder;

public class EchoChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline().addLast((ChannelHandler) new UserEncoder());
        //ch.pipeline().addLast(new EchoClientHandler());
        ch.pipeline().addLast(new UserDecoder());
        ch.pipeline().addLast(new UserHandler());
    }
}

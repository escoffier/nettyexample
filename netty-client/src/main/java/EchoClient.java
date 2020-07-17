//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//
//import java.net.InetSocketAddress;
//
//public class EchoClient {
//    private final String host;
//    private final int port;
//
//    public EchoClient(String host, int port) {
//        this.host = host;
//        this.port = port;
//    }
//
//    public void start() throws Exception {
//        EventLoopGroup group = new NioEventLoopGroup();
//
//
//        try{
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(group)
//                    .channel(NioSocketChannel.class)
//                    .remoteAddress(new InetSocketAddress(host, port))
//                    .handler(new PBChannelInitializer(this));
////                    .handler(new PBChannelInitializer());
//
//            ChannelFuture future = bootstrap.connect().sync();
//
//            future.channel().closeFuture().sync();
//        } finally {
//            group.shutdownGracefully();
//        }
//    }
//
//    public static void main(String[] args) throws Exception{
//        EchoClient client = new EchoClient("192.168.1.209", 19999);
//
//        for (int i = 0; i < 1; i++){
//            client.start();
//        }
//    }
//
//}

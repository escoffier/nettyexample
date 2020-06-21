import handlers.PBChannelInitializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.PBService;
import services.Service;

import java.util.List;

public class EchoServer {

    private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);
//    private int port;
//    EchoServer(int port) {
//        this.port = port;
//    }

    private List<Service> serviceList;

    public EchoServer(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public static void main(String[] args) throws Exception {

//           services.Service service = new services.EchoService(19999);

        ChannelInitializer<SocketChannel> channelChannelInitializer = new PBChannelInitializer();
        Service service = new PBService(19999, channelChannelInitializer);
        service.start();
    }

//    public void start() throws Exception {
//        final handlers.EchoServerHandler handler = new handlers.EchoServerHandler();
//
//        EventLoopGroup group = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap bootstrap = new ServerBootstrap();
//            bootstrap.group(group)
//                    .channel(NioServerSocketChannel.class)
//                    .localAddress(new InetSocketAddress("192.168.1.209",port))
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new util.UserDecoder());
//                            ch.pipeline().addLast(new handlers.EchoOutHandler());
//                            ch.pipeline().addLast(new util.UserEncoder());
//                            ch.pipeline().addLast(new handlers.EchoServerPreHandler());
//                            ch.pipeline().addLast(handler);
////                            ch.pipeline().addLast(new handlers.EchoServerPreHandler());
////                            ch.pipeline().addLast(new handlers.EchoOutHandler());
//                        }
//                    });
//
//            logger.info("Start server at port : " + port);
//            //System.out.println("Start server at port : " + port);
//            ChannelFuture future = bootstrap.bind().sync();
//            future.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    if (future.isSuccess()) {
//                        System.out.println("----bind port " + port);
//                    } else {
//                        Throwable cause = future.cause();
//                        cause.printStackTrace();
//                    }
//
//                }
//            });
//            future.channel().closeFuture().sync();
//        } finally {
//            group.shutdownGracefully();
//
//        }
//    }
}

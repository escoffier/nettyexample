package services;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractService implements Service {
    final int port;
    ChannelInitializer<SocketChannel> channelChannelInitializer;
//    List<ChannelHandler> channelHandlers;

//    public AbstractService(int port,  ChannelHandler... channelHandlers) {
//        this.port = port;
//        if (channelHandlers.length == 0) {
//            this.channelHandlers = new ArrayList<ChannelHandler>();
//        }else {
//            this.channelHandlers = Arrays.asList(channelHandlers);
//        }
//    }
//
//    public AbstractService(int port, List<ChannelHandler> channelHandlers) {
//        this.port = port;
//        this.channelHandlers = channelHandlers;
//    }

    public AbstractService(int port, ChannelInitializer<SocketChannel> channelChannelInitializer) {
        this.port = port;
        this.channelChannelInitializer = channelChannelInitializer;
    }

    @Override
    public void start() {
        doStart();
    }

    protected void doStart() {
//        final handlers.EchoServerHandler handler = new handlers.EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress("192.168.1.209",port))
                    .childHandler(channelChannelInitializer);

            ChannelFuture future = bootstrap.bind().sync();

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("----bind port " + port);
                    } else {
                        Throwable cause = future.cause();
                        cause.printStackTrace();
                    }

                }
            });
            future.channel().closeFuture().sync();
        } catch (Exception ex) {

            //logger.error(ex.getMessage());
        } finally {
            group.shutdownGracefully();

        }
    }
}

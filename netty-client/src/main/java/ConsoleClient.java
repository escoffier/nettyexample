import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proto.message.CheckHealth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class ConsoleClient {
    private final String host;
    private final int port;
    private ChannelFuture future;
    private static Logger logger = LoggerFactory.getLogger(ConsoleClient.class);

    public ConsoleClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ChannelFuture createBootstrap(Bootstrap bootstrap, EventLoopGroup group) {
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new PBChannelInitializer(this));

        future = bootstrap.connect().addListener(new GenericFutureListener<ChannelFuture>() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    logger.info("connect failed " + future.cause().toString());
                    final EventLoop eventLoop = future.channel().eventLoop();
                    eventLoop.schedule(() -> {
                        ChannelFuture newFuture = createBootstrap(new Bootstrap(), eventLoop);
                        ConsoleClient.this.setFuture(newFuture);

                    }, 5L, TimeUnit.SECONDS);
                } else {
                    logger.info("connect to " + future.channel().remoteAddress());
                }
            }
        });
        return future;
    }

    private void sendRequest() {

    }

    public void start1() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        future = createBootstrap(bootstrap, group);

        ChannelFuture lastWriteFuture = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String count;
            try {
                count = in.readLine();
            } catch (IOException exception) {
                continue;
            }

            if (count.equalsIgnoreCase("quit")) {
                //future.channel().close().sync();
                break;
            } else if (count.isEmpty()) {
                continue;
            }

            Integer cnt = Integer.valueOf(count);

            if (cnt <= 0) {
                logger.warn("Invalid cnt");
                continue;
            }

            for (int i = 0; i < cnt; i++) {
                CheckHealth.HealthCheckRequest request = CheckHealth.HealthCheckRequest.newBuilder()
                        .setService("test-service-" + i).build();
                lastWriteFuture = future.channel().writeAndFlush(request);
                lastWriteFuture.addListener(new GenericFutureListener<ChannelFuture>() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            logger.error("write failure: {}", channelFuture.cause().getMessage());
                            channelFuture.cause().printStackTrace(System.err);
                        }
                    }
                });
            }
        }

//        if(lastWriteFuture != null) {
//            lastWriteFuture.sync();
//        }

    }

    public void start() throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new PBChannelInitializer(this));

            ChannelFuture future = bootstrap.connect().sync().addListener(new GenericFutureListener<ChannelFuture>() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        logger.info("connect failed " + future.cause().toString());
                    } else {
                        logger.info("connect to " + future.channel().remoteAddress());
                    }
                }
            });


            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String count = in.readLine();
                if (count.equalsIgnoreCase("quit")) {
                    future.channel().close().sync();
                    break;
                } else if (count.isEmpty()) {
                    continue;
                }

                Integer cnt = Integer.valueOf(count);

                if (cnt <= 0) {
                    System.out.println("Invalid cnt");
                    continue;
                }

                for (int i = 0; i < cnt; i++) {
                    CheckHealth.HealthCheckRequest request = CheckHealth.HealthCheckRequest.newBuilder().setService("test-service-" + i).build();
                    lastWriteFuture = future.channel().writeAndFlush(request);
                    lastWriteFuture.addListener(new GenericFutureListener<ChannelFuture>() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (!future.isSuccess()) {
                                logger.error("write failure: {}", future.cause().getMessage());
                                future.cause().printStackTrace(System.err);
                            }
                        }
                    });
                }
            }

            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
//            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void setFuture(ChannelFuture future) {
        this.future = future;
    }

    public static void main(String[] args) throws Exception {
        ConsoleClient client = new ConsoleClient("192.168.1.209", 19999);
        client.start1();
    }
}

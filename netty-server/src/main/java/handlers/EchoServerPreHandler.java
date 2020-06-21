package handlers;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class EchoServerPreHandler  extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(EchoServerPreHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
//        ByteBuf buffer = (ByteBuf) msg;
//        System.out.println("handlers.EchoServerPreHandler received msg: "+ buffer.toString(CharsetUtil.UTF_8)
//                + "   ---current thread: " + Thread.currentThread().getName());


        logger.info("######  handlers.EchoServerPreHandler received msg: "+ msg.toString()
                + "   ---current thread: " + Thread.currentThread().getName());
//        System.out.println("handlers.EchoServerPreHandler received msg: "+ msg.toString()
//                + "   ---current thread: " + Thread.currentThread().getName());
        ctx.fireChannelRead(msg);
    }
}

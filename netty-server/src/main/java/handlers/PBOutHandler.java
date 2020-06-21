package handlers;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PBOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //发送完 response close channel
//        ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
        final AttributeKey<Integer> counter = AttributeKey.valueOf("counter");
        Integer i = ctx.channel().attr(counter).get();
        Logger logger  = LoggerFactory.getLogger(PBOutHandler.class);
        logger.info("attr {}", i);

        ctx.writeAndFlush(msg);
    }
}

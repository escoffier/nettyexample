package handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoOutHandler extends ChannelOutboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(EchoOutHandler.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //super.write(ctx, msg, promise);
        ByteBuf buffer = (ByteBuf) msg;

        logger.info("msg length: "+  buffer.capacity());

        //ctx.write(msg, promise);
        ctx.writeAndFlush(msg, promise).addListener(ChannelFutureListener.CLOSE);

        //ctx.flush();
        //ctx.writeAndFlush(msg);
        //ctx.fireChannelWritabilityChanged();
    }
}

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proto.message.CheckHealth;

public class PBClientHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(PBClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info(msg.toString());
        ReferenceCountUtil.release(msg);
        //ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        CheckHealth.HealthCheckRequest request= CheckHealth.HealthCheckRequest.newBuilder().setService("test service").build();
//        ctx.writeAndFlush(request);
        logger.info("connected to server " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("channel exception: ", cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}

package handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proto.message.CheckHealth;

public class PBServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger  = LoggerFactory.getLogger(PBServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        final AttributeKey<Integer> counter = AttributeKey.valueOf("counter");
        ctx.channel().attr(counter).set(111);
        CheckHealth.HealthCheckRequest request = (CheckHealth.HealthCheckRequest) msg;
        logger.info("HealthCheckRequest service: {}", request.getService());

        CheckHealth.HealthCheckResponse response = CheckHealth.HealthCheckResponse.newBuilder()
                .setStatus(CheckHealth.HealthCheckResponse.ServingStatus.SERVING).build();
        ctx.writeAndFlush(response);
        ReferenceCountUtil.release(request);
    }
}

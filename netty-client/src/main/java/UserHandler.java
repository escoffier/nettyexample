import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.User;

public class UserHandler extends SimpleChannelInboundHandler<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, User msg) throws Exception {
        logger.info(msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        User user = new User();
        user.setUserName("robbie");
        user.setPassword("1234");
        user.setDescription("from client");
        ctx.writeAndFlush(user);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

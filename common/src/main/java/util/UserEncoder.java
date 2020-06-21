package util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class UserEncoder extends MessageToByteEncoder<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserEncoder.class);
    @Override
    protected void encode(ChannelHandlerContext ctx, User msg, ByteBuf out) throws Exception {
        ByteArrayOutputStream  byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(byteArrayOutputStream);

        output.writeObject(msg);
        output.flush();
        output.close();
        byte[] data = byteArrayOutputStream.toByteArray();
        out.writeBytes(data);

        logger.info(msg.toString());


        //msg.toString();
    }
}


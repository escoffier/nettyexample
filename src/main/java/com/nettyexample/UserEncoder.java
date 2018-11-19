package com.nettyexample;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class UserEncoder extends MessageToByteEncoder<User> {
    @Override
    protected void encode(ChannelHandlerContext ctx, User msg, ByteBuf out) throws Exception {
        ByteArrayOutputStream  byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(byteArrayOutputStream);

        output.writeObject(msg);
        output.flush();
        output.close();
        byte[] data = byteArrayOutputStream.toByteArray();
        out.writeBytes(data);


        //msg.toString();
    }
}


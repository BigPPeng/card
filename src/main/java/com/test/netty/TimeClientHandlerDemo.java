package com.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by cuihp on 2020/10/22.
 */
public class TimeClientHandlerDemo extends ChannelHandlerAdapter {
    private final ByteBuf buffer;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("exception " + cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msg1 = (ByteBuf) msg;
        byte[] bytes = new byte[msg1.readableBytes()];
        buffer.readBytes(bytes);
        String s = new String(bytes, "UTF-8");
        System.out.println("Now is " + s);
    }

    public TimeClientHandlerDemo() {
        byte[] bytes = "order".getBytes();
        buffer = Unpooled.buffer(bytes.length);
        buffer.writeBytes(bytes);
    }



}

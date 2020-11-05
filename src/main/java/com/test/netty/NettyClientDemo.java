package com.test.netty;

import com.card.test.net.nio.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by cuihp on 2020/10/22.
 */
public class NettyClientDemo {
    public void connect(int post, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new TimeClientHandlerDemo());
                }
            });
            ChannelFuture sync = bootstrap.connect(host,post).sync();
            sync.channel().closeFuture().sync();


        } finally {
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception{
        new NettyClientDemo().connect(8081,"127.0.0.1");
    }

}

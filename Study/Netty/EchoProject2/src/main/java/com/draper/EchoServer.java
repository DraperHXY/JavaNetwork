package com.draper;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author draper_hxy
 */
public class EchoServer {
    int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup workder = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss, workder)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                });
        System.out.println("服务器启动中...");
        try {
            ChannelFuture cf = sb.bind(port).sync();
            System.out.println("服务端接口已绑定");
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            workder.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new EchoServer(8080).start();
    }
}

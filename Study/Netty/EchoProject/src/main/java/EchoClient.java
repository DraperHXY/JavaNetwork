import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author draper_hxy
 */
public class EchoClient {

    private String host;
    private int port;

    EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {

            Bootstrap sb = new Bootstrap();

            sb.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            // 连接到服务端，connect 异步连接，调用同步等待，等待连接成功
            ChannelFuture cf = sb.connect().sync();

            // 阻塞，直到客户端通道关闭
            cf.channel().closeFuture().sync();

        } finally {
            // 线程池优雅退出
            group.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        try {
            new EchoClient("127.0.0.1", 8080).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

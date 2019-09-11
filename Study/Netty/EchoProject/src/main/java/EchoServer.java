import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * @author draper_hxy
 */
public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    /**
     * 启动流程
     */
    public void run() throws InterruptedException {
        // 配置服务器线程组
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            // 启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            System.out.println("Echo 服务器已启动中...");

            // 绑定端口，同步等待成功
            ChannelFuture channelFuture = bootstrap.bind(port).sync();


            // 等待服务绑定端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        try {
            new EchoServer(port).run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

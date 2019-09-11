package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author draper_hxy
 */
public class ServerSocketChnnel1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(54321));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            while (socketChannel != null) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int i = socketChannel.read(byteBuffer);
                byteBuffer.flip();
                System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                Thread.sleep(1000L);
            }
            Thread.sleep(1000L);
        }
    }

}

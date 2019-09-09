package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author draper_hxy
 */
@SuppressWarnings("all")
public class Server {

    private static final int port = 54321;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setReuseAddress(true);

        // 1. 一层最外面的循环不停的接收 socket 新连接
        while (true) {
            System.out.println("服务端等待连接");
            // 2. 阻塞连接
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接");

            // 3. 当有新连接接入时，开启新线程去处理
            new Thread(() -> {
                InputStream in = null;
                InputStreamReader reader = null;
                BufferedReader bufferedReader = null;
                try {
                    in = socket.getInputStream();
                    reader = new InputStreamReader(in);
                    bufferedReader = new BufferedReader(reader);

                    String message = null;
                    while ((message = bufferedReader.readLine()) != null){
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

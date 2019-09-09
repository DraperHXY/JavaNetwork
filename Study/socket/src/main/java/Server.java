import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

        Thread readThread = new Thread(() -> {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("服务器已启动");

            while (true) {
                InputStream in = null;

                try {
                    Thread.sleep(1500L);
                    in = socket.getInputStream();

                    byte[] temBuffer = new byte[1024 * 5];
                    int len = in.read(temBuffer);
                    if (len == -1) {
                        Thread.sleep(1000L);
                        continue;
                    } else {
                        byte[] ins = new byte[len];
                        System.arraycopy(temBuffer, 0, ins, 0, len);
                        System.out.println(new String(ins));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        readThread.start();
    }

    private static void close(InputStream inputStream, OutputStream outputStream, Socket s) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

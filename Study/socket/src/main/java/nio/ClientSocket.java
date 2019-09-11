package nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author draper_hxy
 */
public class ClientSocket {

    public static void main(String[] args) {
        Socket socket;
        {
            try {
                socket = new Socket("127.0.0.1", 54321);
                OutputStream outputStream = socket.getOutputStream();
                Scanner scanner = new Scanner(System.in);
                String str = null;
                while ((str = scanner.nextLine()) != null) {
                    outputStream.write(str.getBytes());
                    outputStream.flush();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

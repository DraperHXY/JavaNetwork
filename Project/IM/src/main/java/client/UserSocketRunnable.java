package client;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author draper_hxy
 */
@SuppressWarnings("all")
public class UserSocketRunnable implements Runnable {

    private final String username;
    private final String ip;
    private final int port;
    private Socket socket;
    private OutputStreamWriter writer;
    private OutputStream out;

    public UserSocketRunnable(String username, NetworkInfo networkInfo) {
        this.username = username;
        this.ip = networkInfo.getIp();
        this.port = networkInfo.getPort();
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ip, port);

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.next();

                if (StringUtils.isBlank(message)) {
                    continue;
                }

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write(messageDecorate(message));
                bufferedWriter.flush();
                socket.getOutputStream().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String messageDecorate(String message) {
        return new StringBuilder()
                .append("[").append(ip).append("]")
                .append(username)
                .append("说：")
                .append(message)
                .append("\n")
                .toString();
    }

    private void closeConllection(OutputStream out, OutputStreamWriter writer) {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.draper.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author draper_hxy
 */
public class BioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("the thime server is start in port :" + PORT);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();

                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println("the time server close");
                serverSocket.close();
            }
        }
    }


}

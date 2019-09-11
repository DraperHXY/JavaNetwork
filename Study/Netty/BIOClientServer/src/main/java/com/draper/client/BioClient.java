package com.draper.client;

import java.io.*;
import java.net.Socket;

/**
 * @author draper_hxy
 */
public class BioClient {

    private static final String ip = "127.0.0.1";
    private static final Integer port = 8080;

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new Thread(new PrintRunnable(reader)).start();
            new Thread(new ScannerRunnable(writer)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

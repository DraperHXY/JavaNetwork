package com.draper.server;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * @author draper_hxy
 */
public class TimeServerHandler implements Runnable {

    private final Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String body = null;

            while ((body = in.readLine()) != null && body.length() != 0) {
                System.out.println("the time server receive msg:" + body);
                out.println(new Date().toString() + "\t" + body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

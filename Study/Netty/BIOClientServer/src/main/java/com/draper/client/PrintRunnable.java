package com.draper.client;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author draper_hxy
 */
public class PrintRunnable implements Runnable {

    private final BufferedReader reader;

    public PrintRunnable(BufferedReader reader) {
        this.reader = reader;
    }

    public void run() {
        String msg = null;
        while (true) {
            try {
                if (((msg = reader.readLine()) != null)) {
                    System.out.println("接收到客户端信息" + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

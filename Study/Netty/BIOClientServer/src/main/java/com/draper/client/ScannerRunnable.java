package com.draper.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author draper_hxy
 */
public class ScannerRunnable implements Runnable {

    private final BufferedWriter writer;

    public ScannerRunnable(BufferedWriter writer) {
        this.writer = writer;
    }


    public void run() {
        String body;
        Scanner scanner = new Scanner(System.in);
        try {
            while ((body = scanner.nextLine()) != null && body.length() != 0) {
                System.out.println(body);
                writer.write(body + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

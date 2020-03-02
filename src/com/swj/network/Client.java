package com.swj.network;

import java.io.*;
import java.net.Socket;

/**
 * @author swj
 * @date 2018/2/10
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("hello\n");
            bw.flush();
            String s = br.readLine();
            System.out.println(s);

            bw.write("world\n");
            bw.flush();
            s = br.readLine();
            System.out.println(s);

            bw.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

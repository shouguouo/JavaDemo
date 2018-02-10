package com.swj.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author swj
 * @date 2018/2/10
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket conn = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            String s = br.readLine();
            while (s != null){
                System.out.println(s);
                bw.write(s.toUpperCase() + "\n");
                bw.flush();
                s = br.readLine();
            }
            br.close();
            bw.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

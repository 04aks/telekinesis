package io.github.aks;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 5000;
        try(ServerSocket serverSocket = new ServerSocket(port)){

            System.out.println("eavesdropping on port: " + port);
            Socket socket = serverSocket.accept();
            System.out.println("connected to: " + socket.getInetAddress());

            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream("received.txt");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = is.read()) != -1){
                fos.write(buffer, 0, bytesRead);
            }

            fos.close();
            socket.close();
            System.out.println("file received successfully");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
package io.github.aks.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final int port;
    public TCPServer(int port){
        this.port = port;
    }

    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server listening on port: " + port);

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("[CONNECTION] connected to: " + socket.getInetAddress());


            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

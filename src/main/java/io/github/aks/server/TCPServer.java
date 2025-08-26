package io.github.aks.server;

import io.github.aks.util.Types;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class TCPServer {
    private final int port;
    public static Set<String> callTypeNames =
                Set.of(
                        Types.UploadCallType.toString()
                );

    public TCPServer(int port){
        this.port = port;
    }

    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server listening on port: " + port);

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("[CONNECTION] connected to: " + socket.getInetAddress());

                new Thread(new ClientHandler(socket)).start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

package io.github.aks.server;

import io.github.aks.protocol.CallType;
import io.github.aks.protocol.CallTypeFactory;
import io.github.aks.storage.DiskFileStorage;
import io.github.aks.storage.FileStorage;
import io.github.aks.util.Types;

import java.io.*;
import java.net.Socket;
import java.util.Set;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final FileStorage storage;
    public ClientHandler(Socket socket){
        this.socket = socket;
        this.storage = new DiskFileStorage("received.jpg"); // FOR NOW
    }
    @Override
    public void run() {
        try(InputStream is = socket.getInputStream()){

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String header = reader.readLine();
            String[] parts = header.split(" ");

            if(TCPServer.callTypeNames.contains(parts[0])){
                CallType callType =
                        CallTypeFactory.create("remove this param", socket, storage);
            }

            writer.println(parts[0].toUpperCase() + " READY");
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{socket.close();} catch(IOException ignored) {}
        }
    }
}

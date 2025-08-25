package io.github.aks.server;

import io.github.aks.storage.DiskFileStorage;
import io.github.aks.storage.FileStorage;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

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
            storage.saveFile(is);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{socket.close();} catch(IOException ignored) {}
        }
    }
}

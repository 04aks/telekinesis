package io.github.aks.protocol;

import io.github.aks.storage.FileStorage;
import java.io.*;
import java.net.Socket;

public class UploadCallType implements CallType{
    private final Socket socket;
    private FileStorage fileStorage;
    public UploadCallType(Socket socket, FileStorage fileStorage){
        this.socket = socket;
        this.fileStorage = fileStorage;
    }

    @Override
    public void handle(String header) throws IOException {

        String[] parts = header.split(" ");
        String filename = parts[1];
        Long fileSize = Long.parseLong(parts[2]);

//        fileStorage.saveFile();

    }
}

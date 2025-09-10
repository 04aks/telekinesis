package io.github.aks.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import io.github.aks.storage.FileStorage;

public class DownloadDirCall implements CallType{
    private final FileStorage storage;
    public DownloadDirCall(FileStorage storage){
        this.storage = storage;
    }

    @Override
    public void handle(DataInputStream is, DataOutputStream dos, long size) throws IOException {
        storage.saveDir();
        dos.writeUTF("DONE");
        dos.flush();
    }
    
}

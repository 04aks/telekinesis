package io.github.aks.protocol;

import io.github.aks.storage.FileStorage;
import io.github.aks.util.FileReceiver;
import java.io.*;

public class DownloadFileCall implements CallType{
    private final FileStorage fileStorage;
    public DownloadFileCall(FileStorage fileStorage){
        this.fileStorage = fileStorage;
    }

    @Override
    public void handle(DataInputStream is, DataOutputStream dos, long size) throws IOException {
        byte[] fileData = FileReceiver.receive(is, size);
        fileStorage.saveFile(fileData);
        dos.writeUTF("DONE");
        dos.flush();
    }
}

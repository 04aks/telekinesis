package io.github.aks.protocol;

import io.github.aks.storage.FileStorage;
import java.io.*;

public class UploadCallType implements CallType{
    private final FileStorage fileStorage;
    public UploadCallType(FileStorage fileStorage){
        this.fileStorage = fileStorage;
    }

    @Override
    public void handle(String filename, byte[] data) throws IOException {

        fileStorage.saveFile(filename, data);
    }
}

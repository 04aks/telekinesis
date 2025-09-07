package io.github.aks.protocol;

import java.io.IOException;
import java.io.InputStream;

import io.github.aks.storage.FileStorage;

public class DownloadDirCall implements CallType{
    private final FileStorage storage;
    public DownloadDirCall(FileStorage storage){
        this.storage = storage;
    }

    @Override
    public void handle(InputStream is) throws IOException {
        storage.saveDir();
    }
    
}

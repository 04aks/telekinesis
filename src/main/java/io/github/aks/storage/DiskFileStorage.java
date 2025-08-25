package io.github.aks.storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DiskFileStorage implements FileStorage{
    private final String fileName;
    public DiskFileStorage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void saveFile(InputStream data) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while((bytesRead = data.read(buffer)) != -1){
            fos.write(buffer, 0, bytesRead);
        }
        System.out.println("File {" + fileName + "} was received.");
        fos.close();
    }
}

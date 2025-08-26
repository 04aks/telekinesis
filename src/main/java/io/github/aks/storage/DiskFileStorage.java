package io.github.aks.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiskFileStorage implements FileStorage{
    private final String fileName;
    public DiskFileStorage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void saveFile(String filename, byte[] data) throws IOException {
        File outputFile = new File("received_" + filename);

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(data);

        System.out.println("File {" + fileName + "} was received.");
        fos.close();
    }
}

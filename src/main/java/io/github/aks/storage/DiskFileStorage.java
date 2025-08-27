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
    public void saveFile(byte[] data) throws IOException {
        File outputFile = new File("received_" + fileName);

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(data);

        System.out.println("File {" + outputFile.getName() + "} was received.");
        fos.close();
    }
}

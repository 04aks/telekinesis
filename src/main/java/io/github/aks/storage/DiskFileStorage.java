package io.github.aks.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DiskFileStorage implements FileStorage{
    private final String fileName;
    public DiskFileStorage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void saveFile(byte[] data) throws IOException {
        String home = System.getProperty("user.home");
        Path downloadDir = Paths.get(home, "Downloads");
        Path filePath = downloadDir.resolve("received_" + fileName);

        File outputFile = filePath.toFile();

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(data);

        System.out.println("File {" + outputFile.getName() + "} was saved in {" + filePath + "}");
        fos.close();
    }
}

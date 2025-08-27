package io.github.aks.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileReceiver {
    public static byte[] receive(InputStream is) throws IOException{
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int bytesRead;
        while((bytesRead = is.read(data)) != -1){
            buffer.write(data, 0, bytesRead);
        }
        return buffer.toByteArray();
    }
}

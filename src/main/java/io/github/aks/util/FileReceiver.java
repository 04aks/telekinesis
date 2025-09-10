package io.github.aks.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class FileReceiver {
    public static byte[] receive(DataInputStream dis, long size) throws IOException{
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        long remaining = size;
        while(remaining > 0){
            int bytesRead = dis.read(data, 0, (int)Math.min(data.length, remaining));
            if(bytesRead == -1){
                throw new EOFException("Unexpected end of stream");
            }
            buffer.write(data, 0, bytesRead);
            remaining -= bytesRead;

        }
        return buffer.toByteArray();
    }
}

package io.github.aks.server;

import io.github.aks.exceptions.InvalidHeaderException;
import io.github.aks.protocol.CallType;
import io.github.aks.protocol.CallTypeFactory;
import io.github.aks.protocol.Header;
import io.github.aks.storage.FileStorage;
import io.github.aks.storage.FileStorageFactory;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private final Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try(InputStream is = socket.getInputStream()){

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            /*
             * juggle both Headers and File data with InputStream
             * BufferedReader WITH InputStream was irrational
             */
            while(true){
                String headerRaw;
                try{
                    headerRaw = dis.readUTF();
                }catch(EOFException e){
                    break;
                }
                if(headerRaw == null || headerRaw.isEmpty()) continue;
                Header header = new Header(headerRaw);
                
            
                FileStorage storage = FileStorageFactory.storageUnit(header.getStorageUnit(), header.getFilename());
                CallType callType = CallTypeFactory.create(header.getType().toString(), storage);

                dos.writeUTF("READY");
                dos.flush();

                callType.handle(dis, dos, header.getFileLength());
                System.out.println();
            }

        }catch(InvalidHeaderException e){
            System.err.println(e.getMessage());
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{socket.close();} catch(IOException ignored) {}
        }
    }
}

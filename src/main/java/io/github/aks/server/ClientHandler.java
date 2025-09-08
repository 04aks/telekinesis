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

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String headerRaw = reader.readLine();

            Header header = new Header(headerRaw);
            System.out.println(header.getFilename());

            FileStorage storage = FileStorageFactory.storageUnit(header.getStorageUnit(), header.getFilename());
            CallType callType = CallTypeFactory.create(header.getType().toString(), storage);

            writer.println("READY");

            callType.handle(is);

        }catch(InvalidHeaderException e){
            System.err.println(e.getMessage());
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{socket.close();} catch(IOException ignored) {}
        }
    }
}

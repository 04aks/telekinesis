package io.github.aks.server;

import io.github.aks.exceptions.InvalidCallTypeException;
import io.github.aks.protocol.CallType;
import io.github.aks.protocol.CallTypeFactory;
import io.github.aks.storage.DiskFileStorage;
import io.github.aks.storage.FileStorage;
import io.github.aks.storage.FileStorageFactory;
import io.github.aks.util.Types;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private FileStorage storage;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try(InputStream is = socket.getInputStream()){

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String header = reader.readLine();
            String[] parts = header.split(" ");

            // the header contains 4 parts
            if(parts.length != 4){
                return;
            }
            // irrelevant call type
            Optional<Types> type = Types.fromString(parts[0]);
            if(type.isEmpty() || type == null){
                throw new InvalidCallTypeException(parts[0]);
            }

            storage = FileStorageFactory.storageUnit(parts[3], parts[1]);
            CallType callType = CallTypeFactory.create(parts[0], storage);
            System.out.println(parts[0].toUpperCase() + " READY");
            writer.println("READY");

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int bytesRead;
            while((bytesRead = is.read(data)) != -1){
                buffer.write(data, 0, bytesRead);
            }

            callType.handle(buffer.toByteArray());
        }catch(InvalidCallTypeException e){
            System.err.println(e.getMessage());
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{socket.close();} catch(IOException ignored) {}
        }
    }
}

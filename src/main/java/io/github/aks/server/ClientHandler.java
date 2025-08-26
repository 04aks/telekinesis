package io.github.aks.server;

import io.github.aks.protocol.CallType;
import io.github.aks.protocol.CallTypeFactory;
import io.github.aks.storage.DiskFileStorage;
import io.github.aks.storage.FileStorage;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final FileStorage storage;
    public ClientHandler(Socket socket){
        this.socket = socket;
        this.storage = new DiskFileStorage("received.jpg"); // FOR NOW
    }
    @Override
    public void run() {
        try(InputStream is = socket.getInputStream()){

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String header = reader.readLine();
            String[] parts = header.split(" ");

            if(!TCPServer.callTypeNames.contains(parts[0])){
                // the call doesn't concern us! perhaps
                return;
            }

            CallType callType = CallTypeFactory.create(parts[0], storage);
            System.out.println(parts[0].toUpperCase() + " READY");
            writer.println("READY");

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int bytesRead;
            while((bytesRead = is.read(data)) != -1){
                buffer.write(data, 0, bytesRead);
            }

            callType.handle(parts[1], buffer.toByteArray());
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{socket.close();} catch(IOException ignored) {}
        }
    }
}

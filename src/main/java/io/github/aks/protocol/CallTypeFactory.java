package io.github.aks.protocol;

import io.github.aks.storage.FileStorage;

import java.net.Socket;

public class CallTypeFactory {
    public static CallType create(String type, Socket socket, FileStorage storage){

        CallType callType = null;
        switch(type){
            case "FILE_UPLOAD": callType = new UploadCallType(socket, storage); break;
        }

        return callType;
    }
}

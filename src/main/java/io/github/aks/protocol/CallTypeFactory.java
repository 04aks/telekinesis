package io.github.aks.protocol;

import io.github.aks.storage.FileStorage;

public class CallTypeFactory {
    public static CallType create(String type, FileStorage storage){

        CallType callType = null;
        switch(type){
            case "FILE_UPLOAD": callType = new DownloadFileCall(storage); break;
            case "DIR_UPLOAD": callType = new DownloadDirCall(storage); break;
        }

        return callType;
    }
}

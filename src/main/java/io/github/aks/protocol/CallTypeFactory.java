package io.github.aks.protocol;

import io.github.aks.storage.FileStorage;

public class CallTypeFactory {
    public static CallType create(String type, FileStorage storage){

        CallType callType = null;
        switch(type){
            case "FILE_UPLOAD": callType = new UploadCallType(storage); break;
        }

        return callType;
    }
}

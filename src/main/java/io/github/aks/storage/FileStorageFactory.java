package io.github.aks.storage;

public class FileStorageFactory {
    public static FileStorage storageUnit(String unit, String filename){
        FileStorage storage = null;
        switch(unit){
            case "DISK": storage = new DiskFileStorage(filename); break;
        }

        return storage;
    }
}

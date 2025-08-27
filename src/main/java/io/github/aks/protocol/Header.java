package io.github.aks.protocol;

import io.github.aks.exceptions.InvalidCallTypeException;
import io.github.aks.exceptions.InvalidHeaderException;
import io.github.aks.util.Types;

public class Header {
    private final Types type;
    private final String filename;
    private final long fileLength;
    private final String storageUnit;

    public Header(String raw) throws InvalidHeaderException{
        String[] parts = raw.split(" ");
        if(parts.length != 4){
            throw new InvalidHeaderException("Expected 4 parts but got: " + parts.length);
        }

        try {
            this.type = Types.fromString(parts[0])
                    .orElseThrow(() -> new InvalidCallTypeException(parts[0].toUpperCase()));
        } catch (InvalidCallTypeException e) {
            throw new RuntimeException(e);
        }

        this.filename = parts[1];

        try{
            this.fileLength = Long.parseLong(parts[2]);
        }catch(NumberFormatException e){
            throw new InvalidHeaderException("Invalid file length: " + parts[2]);
        }

        this.storageUnit = parts[3];
    }

    public Types getType() {
        return type;
    }

    public String getFilename() {
        return filename;
    }

    public long getFileLength() {
        return fileLength;
    }

    public String getStorageUnit() {
        return storageUnit;
    }
}

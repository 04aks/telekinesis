package io.github.aks.util;

import java.util.Optional;

public enum Types {
    FILE_UPLOAD;

    public static Optional<Types> fromString(String value){
        try{
            return Optional.of(Types.valueOf(value));
        }catch(IllegalArgumentException e){
            return Optional.empty();
        }
    } 
}

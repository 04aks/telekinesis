package io.github.aks.protocol;

import java.io.IOException;

public interface CallType {
    void handle(String filename, byte[] data) throws IOException;
}

package io.github.aks.protocol;

import java.io.IOException;

public interface CallType {
    void handle(byte[] data) throws IOException;
}

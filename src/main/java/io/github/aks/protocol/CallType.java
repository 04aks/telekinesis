package io.github.aks.protocol;

import java.io.IOException;

public interface CallType {
    void handle(String header) throws IOException;
}

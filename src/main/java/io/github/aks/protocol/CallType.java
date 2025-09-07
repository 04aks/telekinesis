package io.github.aks.protocol;

import java.io.IOException;
import java.io.InputStream;

public interface CallType {
    void handle(InputStream is) throws IOException;
}

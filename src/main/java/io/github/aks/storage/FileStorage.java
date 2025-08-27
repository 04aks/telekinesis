package io.github.aks.storage;

import java.io.IOException;

public interface FileStorage {
    void saveFile(byte[] data) throws IOException;
}

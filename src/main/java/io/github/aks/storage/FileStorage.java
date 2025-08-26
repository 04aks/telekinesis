package io.github.aks.storage;

import java.io.IOException;

public interface FileStorage {
    void saveFile(String filename, byte[] data) throws IOException;
}

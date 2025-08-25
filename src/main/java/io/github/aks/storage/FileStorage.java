package io.github.aks.storage;

import java.io.IOException;
import java.io.InputStream;

public interface FileStorage {
    void saveFile(InputStream data) throws IOException;
}

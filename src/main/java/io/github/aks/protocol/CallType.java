package io.github.aks.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface CallType {
    void handle(DataInputStream is, DataOutputStream dos, long size) throws IOException;
}

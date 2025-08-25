package io.github.aks;

import io.github.aks.server.TCPServer;

public class Main {
    public static void main(String[] args) {
        int port = 5000;
        TCPServer server = new TCPServer(port);
        server.start();
    }
}

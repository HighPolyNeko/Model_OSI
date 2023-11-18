package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    int port;

    public Server() {
        port = 8080;
    }

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {

        try (ServerSocket serverSocket = new ServerSocket(port);) { // запуск сервера
            while (true) {
                try (Socket clientSocket = serverSocket.accept();) { // ждем подключения

                    try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    ) {
                        System.out.println("Новое подключение!");
                        System.out.printf("Клиент %s, успешно подключился его IP: %s, порт: %d\n", in.readLine(), clientSocket.getInetAddress(), clientSocket.getPort());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

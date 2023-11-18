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
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(() -> {
                    try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                        System.out.println("Новое подключение!");
                        System.out.printf("Клиент успешно подключился его IP: %s, порт: %d\n", clientSocket.getInetAddress(), clientSocket.getPort());

                        out.println("Введите ваше имя: ");
                        out.flush();

                        String name = in.readLine();

                        out.println("Сколько вам лет?: ");
                        out.flush();

                        int age = Integer.parseInt(in.readLine());

                        if (age < 18) {
                            out.println("Доступ закрыт.");
                            out.flush();
                        }
                        else {
                            out.printf("Добро пожаловать, %s!\n", name);
                            out.flush();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

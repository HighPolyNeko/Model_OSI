package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public Client(){ };

    public void connect(String host, int port) {
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            Scanner sc = new Scanner(System.in);

            System.out.print(in.readLine()); // Чтение приветствия от сервера
            out.println(sc.nextLine()); // Отправка имени на сервер

            System.out.print(in.readLine()); // Чтение ответа от сервера
            out.println(sc.nextInt()); // Отправка возраста

            System.out.print(in.readLine()); // Чтение ответа от сервера

            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

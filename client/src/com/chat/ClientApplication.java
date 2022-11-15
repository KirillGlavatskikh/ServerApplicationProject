package com.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApplication {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {

        try(Socket socket = new Socket(HOST, PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in)) {

            System.out.println("Соединение с сервером установлено");

            boolean count = true;
            while(count) {
                System.out.println("Введите сообщение");
                String request = scanner.nextLine();
                if(request.equals("exit")) {
                    count = false;
                }
                else {
                    writer.write(request + "\n");
                    writer.flush();

                    String responseFromServer = reader.readLine();
                    System.out.println(responseFromServer);
                }
            }
        } catch (IOException e) {
            System.out.printf("Не удалось подключиться к серверу с таким хостом - %s и с таким портом - %s", HOST, PORT);
        }

    }
}

package com.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {

    private static final int SERVER_PORT = 8080;
    private static int clientPort;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    clientPort = clientSocket.getPort();
                    System.out.println("Присоединился клиент: " + clientPort);


                    while (true) {

                        String message = reader.readLine();
                        System.out.printf("Клиент %s отправил сообщение ' %s ' \n", clientPort, message);

                        writer.write(String.format("Сервер получил сообщение ' %s ' \n", message));
                        writer.flush();
                    }
                } catch (Exception e) {
                    System.out.printf("Клиент %s отсоединился от сервера \n", clientPort);
                }
            }

        } catch (IOException e) {
            System.out.printf("Не удалось подключиться к порту %s", SERVER_PORT);
        }
    }
}

package com.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {

    private static final int SERVER_PORT = 8080;
    private static int clientPort;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("������ �������");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    clientPort = clientSocket.getPort();
                    System.out.println("������������� ������: " + clientPort);


                    while (true) {

                        String message = reader.readLine();
                        System.out.printf("������ %s �������� ��������� ' %s ' \n", clientPort, message);

                        writer.write(String.format("������ ������� ��������� ' %s ' \n", message));
                        writer.flush();
                    }
                } catch (Exception e) {
                    System.out.printf("������ %s ������������ �� ������� \n", clientPort);
                }
            }

        } catch (IOException e) {
            System.out.printf("�� ������� ������������ � ����� %s", SERVER_PORT);
        }
    }
}

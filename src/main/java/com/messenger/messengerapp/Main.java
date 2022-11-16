package com.messenger.messengerapp;

import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("try connect");
            try {
                Socket socket = new Socket("localhost", 8080);
                System.out.println("connected to server");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                while (true) {
                    String command = consoleReader.readLine();
                    if (command.equals("exit")) {
                        break;
                    }

                    switch (command) {
                        case "serverTime": {
                            getServerAnswer(writer, command, reader, "server time is ");
                            break;
                        }
                        case "add": {
                            addUser(consoleReader, reader, writer, command);
                            break;
                        }
                        case "login": {
                            writer.println(command);
                            writer.flush();
                            System.out.print("Enter login: ");
                            String login = consoleReader.readLine();
                            System.out.print("Enter password: ");
                            String pass = consoleReader.readLine();
                            writer.println(login);
                            getServerAnswer(writer, pass, reader, "Server answer = ");
                            break;
                        }
                        case "delete": {
                            writer.println(command);
                            writer.flush();
                            System.out.print("Enter login for delete: ");
                            String login = consoleReader.readLine();
                            writer.println(login);
                            System.out.println("User " + login + " deleted");
                            break;
                        }

                        case "message": {
                            writer.println(command);
                            writer.flush();
                            System.out.println("Enter user to send message: ");
                            String userToSendMessage = consoleReader.readLine();
                            writer.println(userToSendMessage);
                            System.out.println("Enter text message:");
                            String textMessage = consoleReader.readLine();
                            writer.println(textMessage);
                            break;
                        }

                        default:
                            System.out.println("unknown command");
                    }

                    writer.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("disconnected from server");
        }
    }

    private static void getServerAnswer(PrintWriter writer, String command, BufferedReader reader, String x) throws IOException {
        writer.println(command);
        writer.flush();
        String answer = reader.readLine();
        System.out.println(x + answer);
    }

    private static void addUser(BufferedReader consoleReader, BufferedReader reader, PrintWriter writer, String command) throws IOException {
        writer.println(command);
        writer.flush();
        System.out.print("Enter name: ");
        String name = consoleReader.readLine();
        System.out.print("Enter password: ");
        String password = consoleReader.readLine();
        writer.println(name);
        getServerAnswer(writer, password, reader, "Server answer = ");
    }
}

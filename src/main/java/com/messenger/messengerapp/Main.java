package com.messenger.messengerapp;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.Socket;

public class Main {
    static String token = "";
    static int userId = 0;

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
                            writer.println(pass);
                            getServerAnswer(writer, pass, reader, "Server answer = ");
                            userId = Integer.parseInt(reader.readLine());
                            System.out.println("Userid is: " + userId);
                            token = reader.readLine();
                            System.out.println("Your token is: " + token);
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

                        case "sendMessage": {
                            writer.println(command);
                            writer.flush();
                            System.out.println("Enter user to send message: ");
                            String userToSendMessage = consoleReader.readLine();
                            writer.println(userToSendMessage);
                            writer.flush();
                            //repeat send command if not user
                            String answer = reader.readLine();
                            System.out.println("Server answer = " + answer + "\n" + "User not found. Try again");
                            writer.println(command);
                            writer.flush();

                            System.out.println("Enter text message:");
                            String textMessage = consoleReader.readLine();
                            String userIdStr = Integer.toString(userId);
                            writer.println(userIdStr);
                            writer.flush();
                            writer.println(textMessage);
                            writer.flush();
                            writer.println(token);
                            writer.flush();
                            break;
                        }
                        case "getUser": {
                            writer.println(command);
                            writer.flush();
                            System.out.println("Enter userId");
                            String userId = consoleReader.readLine();
                            writer.println(userId);
                            writer.flush();
                            writer.println(token);
                            writer.flush();
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

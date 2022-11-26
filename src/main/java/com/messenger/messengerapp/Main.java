package com.messenger.messengerapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private static String token = "";
    private static int userId = 0;
    private static List<String> messagesList = new ArrayList<>();
    private static Socket socket;


    public static void main(String[] args) {
        System.out.println("Hello world");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("try connect");
            try {
                socket = new Socket("localhost", 8080);
                System.out.println("connected to server");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                while (true) {
                    String command = consoleReader.readLine();

                    if (command.equals("exit")) {
                        break;
                    }

                    switch (command) {
                        case "/serverTime": {
                            getServerAnswer(writer, command, reader, "server time is ");
                            break;
                        }
                        case "/register": {
                            addUser(consoleReader, reader, writer, command);
                            break;
                        }
                        case "/login": {
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
                        case "/delete": {
                            writer.println(command);
                            writer.flush();
                            System.out.print("Enter login for delete: ");
                            String login = consoleReader.readLine();
                            writer.println(login);
                            System.out.println("User " + login + " deleted");
                            break;
                        }

                        case "/sendMessage": {
                            writer.println(command);
                            writer.flush();
                            System.out.println("Enter user name to send message: ");
                            String userToSendMessage = consoleReader.readLine();
                            System.out.println("Enter text message:");
                            String textMessage = consoleReader.readLine();
                            writer.println(userToSendMessage);
                            writer.println(textMessage);
                            writer.println(token);
                            writer.flush();

                            //answer
                            String answer = reader.readLine();
                            System.out.println("Server answer = " + answer);

                            break;
                        }

                        case "/readMessages": {
                            writer.println(command);
                            System.out.println("Enter since_date");
                            writer.println(consoleReader.readLine());
                            writer.println(token);
                            writer.flush();


                            boolean isSuccess = reader.readLine().equals("true");

                            if (isSuccess) {
                                Integer messagesCount = Integer.valueOf(reader.readLine());
                                ArrayList<Message> messages = new ArrayList<>();
                                for (int i = 0; i < messagesCount; i++) {
                                    Message message = new Message();
                                    message.messageId = Integer.parseInt(reader.readLine());
                                    message.fromUserId = Integer.parseInt(reader.readLine());
                                    message.toUserId = Integer.parseInt(reader.readLine());
                                    message.message = reader.readLine();
                                    message.date = Long.parseLong(reader.readLine());
                                    messages.add(message);
                                }
                                printMessages(messages);
                            } else {
                                String failureReason = reader.readLine();
                                System.out.println("failed to read messages=" + failureReason);
                            }
                            //answer

                            break;
                        }

                        case "/getUserById": {
                            writer.println(command);
                            writer.flush();
                            System.out.println("Enter userId");
                            String userId = consoleReader.readLine();
                            writer.println(userId);
                            writer.flush();
                            writer.println(token);
                            writer.flush();

                            //answer
                            String answer = reader.readLine();
                            System.out.println("Username: " + answer);
                            break;
                        }
                        case "/getUserByLogin": {
                            writer.println(command);
                            writer.flush();
                            System.out.println("Enter username");
                            String userName = consoleReader.readLine();
                            writer.println(userName);
                            writer.flush();
                            writer.println(token);
                            writer.flush();

                            //answer
                            String answer = reader.readLine();
                            System.out.println("Username: " + answer);
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

    private static void printMessages(ArrayList<Message> messages) {

        System.out.println("print messages, count = " + messages.size());
        for (Message message : messages) {
            System.out.println(message);
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

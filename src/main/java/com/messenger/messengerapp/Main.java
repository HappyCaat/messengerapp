package com.messenger.messengerapp;

import com.sun.jdi.PathSearchingVirtualMachine;

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
                        case "calcSum": {
                            System.out.println("You trying to calc sum, please enter two numbers");
                            String numberA = consoleReader.readLine();
                            String numberB = consoleReader.readLine();
                            writer.println(command);
                            writer.println(numberA);
                            writer.println(numberB);
                            writer.flush();
                            String answer = reader.readLine();
                            System.out.println("Answer is: " + answer);
                        }
                        break;
                        case "serverTime": {
                            writer.println(command);
                            writer.flush();

                            String answer = reader.readLine();
                            System.out.println("server time is " + answer);
                            break;
                        }
                        case "register": {
                            writer.println(command);
                            writer.flush();
                            System.out.println("Enter name and password");
                            String name = consoleReader.readLine();
                            String password = consoleReader.readLine();
                            writer.println(name);
                            writer.println(password);
                            writer.flush();

                            String answer = reader.readLine();
                            System.out.println("Server answer = " + answer);
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
}

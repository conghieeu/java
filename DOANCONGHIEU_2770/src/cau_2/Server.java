/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau_2;

/**
 *
 * @author congh
 */
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            System.out.println("Server is listening on port 22222");
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String input = in.readLine();
            if (input != null) {
                int charCount = input.length();
                int digitCount = 0;
                int letterCount = 0;
                Map<Character, Integer> charFrequency = new HashMap<>();

                for (char c : input.toCharArray()) {
                    if (Character.isDigit(c)) {
                        digitCount++;
                    }
                    if (Character.isLetter(c)) {
                        letterCount++;
                    }
                    charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
                }

                StringBuilder duplicateChars = new StringBuilder();
                for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
                    if (entry.getValue() > 1) {
                        duplicateChars.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    }
                }

                String reversedString = new StringBuilder(input).reverse().toString();

                out.println("So luong ky tu: " + charCount);
                out.println("So luong chu so: " + digitCount);
                out.println("So luong chu cai: " + letterCount);
                out.println("So luong ky tu trung lap:\n" + duplicateChars.toString());
                out.println("Day ky tu dao nguoc: " + reversedString);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package Bai4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Bai4 {
    public static void main(String[] args) {
        new Thread(Bai4::startServer).start();
        startClient();
    }

    // Phan Server
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(44444)) {
            System.out.println("Server dang lang nghe o cong 44444");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client moi da ket noi");

                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    int number = input.readInt();
                    boolean isPalindrome = checkPalindrome(number);

                    output.writeBoolean(isPalindrome);
                } catch (IOException e) {
                    System.out.println("Loi server: " + e.getMessage());
                }
            } 
        } catch (IOException e) {
            System.out.println("Loi server: " + e.getMessage());
        }
    }

    // Ham kiem tra so doi xung
    public static boolean checkPalindrome(int number) {
        String str = Integer.toString(number);
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // Phan Client
    public static void startClient() {
        String hostname = "localhost";
        int port = 44444;

        try (Socket socket = new Socket(hostname, port)) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Bat dau chuong trinh kiem tra so doi xung");
            System.out.print("Nhap mot so: ");
            int number = scanner.nextInt();

            output.writeInt(number);

            boolean isPalindrome = input.readBoolean();
            if (isPalindrome) {
                System.out.println("So " + number + " la so doi xung.");
            } else {
                System.out.println("So " + number + " khong phai la so doi xung.");
            } 
        } catch (IOException e) {
            System.out.println("Loi I/O: " + e.getMessage());
        }
    }
}
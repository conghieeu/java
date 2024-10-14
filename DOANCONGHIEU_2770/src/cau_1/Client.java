package cau_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        startClient();
    }

    public static void startClient() {
        String hostname = "localhost";
        int port = 11111;

        try (Socket socket = new Socket(hostname, port)) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nhap so nguyen a: ");
            int a = scanner.nextInt();
            System.out.print("Nhap so nguyen b: ");
            int b = scanner.nextInt();

            output.writeInt(a);
            output.writeInt(b);

            int sum = input.readInt();
            System.out.println("Tong cua " + a + " va " + b + " la: " + sum);
        } catch (IOException e) {
            System.out.println("Loi I/O: " + e.getMessage());
        }
    }
}

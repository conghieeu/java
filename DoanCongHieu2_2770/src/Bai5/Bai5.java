package Bai5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Bai5 {
    public static void main(String[] args) {
        new Thread(Bai5::startServer).start();
        startClient();
    }

    // Phan Server
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(55555)) {
            System.out.println("Server dang lang nghe o cong 55555");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client moi da ket noi");

                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    int a = input.readInt();
                    int b = input.readInt();
                    int c = input.readInt();

                    String result = checkTriangle(a, b, c);

                    output.writeUTF(result);
                } catch (IOException e) {
                    System.out.println("Loi server: " + e.getMessage());
                }
            }
 
        } catch (IOException e) {
            System.out.println("Loi server: " + e.getMessage());
        }
    }

    // Ham kiem tra tam giac
    public static String checkTriangle(int a, int b, int c) {
        if (a + b > c && a + c > b && b + c > a) {
            if (a == b && b == c) {
                return "Day la tam giac deu.";
            } else if (a == b || b == c || a == c) {
                return "Day la tam giac can.";
            } else if (a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a) {
                return "Day la tam giac vuong.";
            } else {
                return "Day la tam giac thuong.";
            }
        } else {
            return "Day khong phai la tam giac.";
        }
    }

    // Phan Client
    public static void startClient() {
        String hostname = "localhost";
        int port = 55555;

        try (Socket socket = new Socket(hostname, port)) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Bat dau client chuong trinh kiem tra hinh tam giac vuong, thuong, can");
            System.out.print("Nhap canh a: ");
            int a = scanner.nextInt();
            System.out.print("Nhap canh b: ");
            int b = scanner.nextInt();
            System.out.print("Nhap canh c: ");
            int c = scanner.nextInt();

            output.writeInt(a);
            output.writeInt(b);
            output.writeInt(c);

            String result = input.readUTF();
            System.out.println(result);
 
        } catch (IOException e) {
            System.out.println("Loi I/O: " + e.getMessage());
        }
    }
}
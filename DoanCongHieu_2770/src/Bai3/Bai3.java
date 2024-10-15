package Bai3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {
        new Thread(Bai3::startServer).start();
        startClient();
    }

    // Phan Server
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(33333)) {
            System.out.println("Server dang lang nghe o cong 33333");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client moi da ket noi");

                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    double a = Double.parseDouble(input.readLine());
                    double b = Double.parseDouble(input.readLine());
                    double c = Double.parseDouble(input.readLine());

                    if (a == 0) {
                        output.println("Khong phai phuong trinh bac 2");
                    } else {
                        double delta = b * b - 4 * a * c;
                        if (delta < 0) {
                            output.println("Phuong trinh vo nghiem");
                        } else if (delta == 0) {
                            double x = -b / (2 * a);
                            output.println("Phuong trinh co nghiem kep: x = " + x);
                        } else {
                            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                            output.println("Phuong trinh co hai nghiem: x1 = " + x1 + ", x2 = " + x2);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Loi server: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Loi server: " + e.getMessage());
        }
    }

    // Phan Client
    public static void startClient() {
        String hostname = "localhost";
        int port = 33333;

        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Bat dau chuong trinh, nghiem cua phuong trinh bat 2: ");
            System.out.print("Nhap he so a: ");
            double a = scanner.nextDouble();
            System.out.print("Nhap he so b: ");
            double b = scanner.nextDouble();
            System.out.print("Nhap he so c: ");
            double c = scanner.nextDouble();

            output.println(a);
            output.println(b);
            output.println(c);

            String result = input.readLine();
            System.out.println(result); 
        } catch (IOException e) {
            System.out.println("Loi I/O: " + e.getMessage());
        }
    }
}

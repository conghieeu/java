package Bai1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        new Thread(Bai1::startServer).start();
        startClient();
    }

    // Phan Server
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(11111)) {
            System.out.println("Server dang lang nghe o cong 11111");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client moi da ket noi");

                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    int a = input.readInt();
                    int b = input.readInt();

                    StringBuilder expression = new StringBuilder("S = ");
                    double sum = 0;
                    for (int i = 1; i <= b; i++) {
                        sum += (double) a / i;
                        if (i == 1) {
                            expression.append(a);
                        } else {
                            expression.append(" + ").append(a).append("/").append(i);
                        }
                    }

                    output.writeUTF(expression.toString());
                    output.writeDouble(sum);
                } catch (IOException e) {
                    System.out.println("Loi server: " + e.getMessage());
                }
            }
        } catch (BindException e) {
            System.out.println("Server da duoc chay tren cong 11111");
        } catch (IOException e) {
            System.out.println("Loi server: " + e.getMessage());
        }
    }

    // Phan Client
    public static void startClient() {
        String hostname = "localhost";
        int port = 11111;

        try (Socket socket = new Socket(hostname, port)) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            System.out.println("Bat dau chuong trinh tinh tong S");
            System.out.print("Nhap so nguyen a: ");
            int a = scanner.nextInt();
            System.out.print("Nhap so nguyen b: ");
            int b = scanner.nextInt();

            output.writeInt(a);
            output.writeInt(b);

            String expression = input.readUTF();
            double result = input.readDouble();
            System.out.println(expression + " = " + result);
        } catch (IOException e) {
            System.out.println("Loi I/O: " + e.getMessage());
        }
    }
}

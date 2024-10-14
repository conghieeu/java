package cau_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        new Thread(Server::startServer).start();
    }

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
                    int sum = a + b;

                    output.writeInt(sum);
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
}

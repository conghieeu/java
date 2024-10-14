/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau_3;

/**
 *
 * @author congh
 */
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");
            while (true) {
                try (Socket socket = serverSocket.accept(); 
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

                    double a = input.readDouble();
                    double b = input.readDouble();
                    double c = input.readDouble();

                    double delta = b * b - 4 * a * c;
                    String result;

                    if (delta > 0) {
                        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                        result = "Nghiệm x1 = " + x1 + ", x2 = " + x2;
                    } else if (delta == 0) {
                        double x = -b / (2 * a);
                        result = "Nghiệm kép x = " + x;
                    } else {
                        result = "Phương trình vô nghiệm";
                    }

                    output.writeUTF(result);
                } catch (IOException ex) {
                    System.out.println("Server exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

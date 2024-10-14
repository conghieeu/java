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

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 22222); 
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Nhap doan ky tu: ");
            String userInput = stdIn.readLine();
            out.println(userInput);

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

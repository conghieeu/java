/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau_4;

/**
 *
 * @author congh
 */
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        JTextField numberField = new JTextField(10);
        JButton sendButton = new JButton("Gửi");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nhập số:"));
        panel.add(numberField);
        panel.add(sendButton);

        frame.add(panel);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        sendButton.addActionListener(e -> {
            try (Socket socket = new Socket("localhost", 12345); 
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 
                    DataInputStream input = new DataInputStream(socket.getInputStream())) {

                int number = Integer.parseInt(numberField.getText());
                output.writeInt(number);

                String result = input.readUTF();
                JOptionPane.showMessageDialog(frame, result);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}

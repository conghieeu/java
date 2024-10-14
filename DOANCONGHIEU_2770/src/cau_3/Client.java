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
import javax.swing.*;

public class Client {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        JTextField aField = new JTextField(5);
        JTextField bField = new JTextField(5);
        JTextField cField = new JTextField(5);
        JButton sendButton = new JButton("Gửi");

        JPanel panel = new JPanel();
        panel.add(new JLabel("a:"));
        panel.add(aField);
        panel.add(new JLabel("b:"));
        panel.add(bField);
        panel.add(new JLabel("c:"));
        panel.add(cField);
        panel.add(sendButton);

        frame.add(panel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        sendButton.addActionListener(e -> {
            try (Socket socket = new Socket("localhost", 12345); 
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 
                    DataInputStream input = new DataInputStream(socket.getInputStream())) {

                double a = Double.parseDouble(aField.getText());
                double b = Double.parseDouble(bField.getText());
                double c = Double.parseDouble(cField.getText());

                output.writeDouble(a);
                output.writeDouble(b);
                output.writeDouble(c);

                String result = input.readUTF();
                JOptionPane.showMessageDialog(frame, result);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}

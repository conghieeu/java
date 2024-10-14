/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau_5;

/**
 *
 * @author congh
 */
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
            try (DatagramSocket socket = new DatagramSocket()) {
                String message = aField.getText() + "," + bField.getText() + "," + cField.getText();
                byte[] buffer = message.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 12345);
                socket.send(packet);

                byte[] responseBuffer = new byte[256];
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                socket.receive(responsePacket);

                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                JOptionPane.showMessageDialog(frame, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

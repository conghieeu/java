package Bai2;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bai2 {

    public static void main(String[] args) {
        new Thread(Bai2::startServer).start();
        startClient();
    }

    // Phan Server
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            System.out.println("Server dang lang nghe o cong 22222");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client moi da ket noi");

                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    char feature = input.readLine().charAt(0);
                    String message = input.readLine();

                    switch (feature) {
                        case 'a':
                            int nonDigitCount = 0;
                            for (char c : message.toCharArray()) {
                                if (!Character.isDigit(c)) {
                                    nonDigitCount++;
                                }
                            }   output.println(nonDigitCount);
                            break;
                        case 'b':
                            Map<Character, Integer> charCountMap = new HashMap<>();
                            for (char c : message.toLowerCase().toCharArray()) {
                                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
                            }   for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
                                output.println(entry.getKey() + ": " + entry.getValue());
                            }   output.println("END");
                            break;
                        case 'c':
                            String[] words = message.split(" ");
                            StringBuilder capitalizedMessage = new StringBuilder();
                            for (String word : words) {
                                if (word.length() > 0) {
                                    capitalizedMessage.append(Character.toUpperCase(word.charAt(0)))
                                            .append(word.substring(1)).append(" ");
                                }
                            }   output.println(capitalizedMessage.toString().trim());
                            break;
                        default:
                            break;
                    }
                } catch (IOException e) {
                    System.out.println("Loi server: " + e.getMessage());
                }
            }
        } catch (BindException e) {
            System.out.println("Server da duoc chay tren cong 22222");
        } catch (IOException e) {
            System.out.println("Loi server: " + e.getMessage());
        }
    }

    // Phan Client
    public static void startClient() {
        String hostname = "localhost";
        int port = 22222;

        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Chon tinh nang (a-Dem ky tu khong phai la so, b-Dem so luong ky tu trung lap, c-Viet hoa ky tu dau tien cua moi tu): ");
            char feature = scanner.next().charAt(0);
            scanner.nextLine();

            System.out.print("Nhap doan ky tu: ");
            String message = scanner.nextLine();

            output.println(feature);
            output.println(message);

            switch (feature) {
                case 'a':
                    int nonDigitCount = Integer.parseInt(input.readLine());
                    System.out.println("So luong ky tu khong phai la so: " + nonDigitCount);
                    break;
                case 'b':
                    String line;
                    while (!(line = input.readLine()).equals("END")) {
                        System.out.println(line);
                    }   break;
                case 'c':
                    String capitalizedMessage = input.readLine();
                    System.out.println("Doan ky tu sau khi viet hoa: " + capitalizedMessage);
                    break;
                default:
                    break;
            } 
        } catch (IOException e) {
            System.out.println("Loi I/O: " + e.getMessage());
        }
    }
}

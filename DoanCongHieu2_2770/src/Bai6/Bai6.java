
import java.io.*;
import java.net.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;

package Bai6;
 
public class Bai6 {

    public class AuthenticationServer {

        private static final String USERNAME = "CS420";
        private static final String PASSWORD = "123";

        public static void main(String[] args) {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                System.out.println("Server is listening on port 12345");
                while (true) {
                    try (Socket socket = serverSocket.accept()) {
                        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                        int attempts = 0;
                        boolean authenticated = false;

                        while (attempts < 3 && !authenticated) {
                            String message = input.readLine();
                            if (message.equalsIgnoreCase("stop")) {
                                break;
                            }

                            String[] credentials = message.split(";");
                            if (credentials.length != 2) {
                                output.println("Định dạng không đúng. Vui lòng nhập lại.");
                                continue;
                            }

                            String user = credentials[0];
                            String pass = credentials[1];

                            if (USERNAME.equals(user) && PASSWORD.equals(pass)) {
                                output.println("Bạn đã truy cập thành công.");
                                authenticated = true;
                            } else {
                                attempts++;
                                if (attempts < 3) {
                                    output.println("User hoặc Password của bạn không đúng, yêu cầu nhập lại.");
                                } else {
                                    output.println("Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.");
                                }
                            }
                        }

                        if (!authenticated) {
                            socket.close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class AuthenticationClient {

        public static void main(String[] args) {
            try (Socket socket = new Socket("localhost", 12345)) {
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("Nhập User và Password (cách nhau bởi dấu ';') hoặc 'stop' để kết thúc:");
                    String message = scanner.nextLine();
                    output.println(message);

                    if (message.equalsIgnoreCase("stop")) {
                        break;
                    }

                    String response = input.readLine();
                    System.out.println(response);

                    if (response.equals("Bạn đã truy cập thành công.") || response.equals("Bạn đã nhập sai 3 lần, bạn đã hết quyền truy cập vào hệ thống.")) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

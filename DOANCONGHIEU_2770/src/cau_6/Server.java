/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cau_6;

/**
 *
 * @author congh
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            TriangleCheckerImpl checker = new TriangleCheckerImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TriangleChecker", checker);
            System.out.println("Server is ready");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
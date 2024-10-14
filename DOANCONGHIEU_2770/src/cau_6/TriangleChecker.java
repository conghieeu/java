/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cau_6;

/**
 *
 * @author congh
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TriangleChecker extends Remote {
    String checkTriangle(double a, double b, double c) throws RemoteException;
}
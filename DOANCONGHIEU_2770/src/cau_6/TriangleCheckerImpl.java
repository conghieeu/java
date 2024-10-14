/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau_6;

/**
 *
 * @author congh
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TriangleCheckerImpl extends UnicastRemoteObject implements TriangleChecker {

    protected TriangleCheckerImpl() throws RemoteException {
        super();
    }

    @Override
    public String checkTriangle(double a, double b, double c) throws RemoteException {
        if (a + b > c && a + c > b && b + c > a) {
            if (a == b && b == c) {
                return "Tam giác đều";
            } else if (a == b || b == c || a == c) {
                if (isRightTriangle(a, b, c)) {
                    return "Tam giác vuông cân";
                }
                return "Tam giác cân";
            } else if (isRightTriangle(a, b, c)) {
                return "Tam giác vuông thường";
            } else {
                return "Tam giác thường";
            }
        } else {
            return "Không phải là tam giác";
        }
    }

    private boolean isRightTriangle(double a, double b, double c) {
        double[] sides = {a, b, c};
        java.util.Arrays.sort(sides);
        return Math.pow(sides[0], 2) + Math.pow(sides[1], 2) == Math.pow(sides[2], 2);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Server;

import RMI.Interface.Calculator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Administrator
 */
public class CalculatorImp extends UnicastRemoteObject implements Calculator {
    public CalculatorImp() throws RemoteException {
        super();
    }
    
    @Override
    public int add(int a, int b) throws Exception {
        System.out.println("Client call...");
        return a+b;
    }
}

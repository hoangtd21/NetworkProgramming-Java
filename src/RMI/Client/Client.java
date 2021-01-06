/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Client;

import RMI.Interface.Calculator;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Administrator
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Calculator cal = (Calculator) Naming.lookup("rmi://localhost:4000/cal");
        System.out.println("Sum = " + cal.add(10, 20));
        
        OKImp ok = new OKImp();
        Naming.bind("rmi://localhost:4000/ok", ok);
        System.out.println("Response...");
    }
}

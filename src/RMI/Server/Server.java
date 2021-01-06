/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Server;

import RMI.Interface.OK;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Administrator
 */
public class Server {
    public static void main(String[] args) throws Exception {
        CalculatorImp cal = new CalculatorImp();
        LocateRegistry.createRegistry(4000);
        Naming.bind("rmi://localhost:4000/cal", cal);
        System.out.println("Server started...");
        
        OK ok = (OK) Naming.lookup("rmi://localhost:4000/ok");
        System.out.println(ok.sayOK());
    }
}

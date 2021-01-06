/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Client;

import RMI.Interface.OK;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Administrator
 */
public class OKImp extends UnicastRemoteObject implements OK {
    public OKImp() throws RemoteException {
        super();
    }
    
    @Override
    public String sayOK() throws Exception {
        return "OK!";
    }
}

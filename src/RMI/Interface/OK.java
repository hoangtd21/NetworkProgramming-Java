/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interface;

import java.rmi.Remote;

/**
 *
 * @author Administrator
 */
public interface OK extends Remote {
    public String sayOK() throws Exception;
}

package UDP.Data;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerData extends Thread {
    public void run() {
        try {
            DatagramSocket s = new DatagramSocket(8000);
            DatagramPacket pkSend;
            DatagramPacket pkRcv;
            byte[] bytes;
            while(true) {
                try {
                    bytes = new byte[1024];
                    pkRcv = new DatagramPacket(bytes, bytes.length);
                    s.receive(pkRcv);
                    System.out.println(new String(pkRcv.getData()));
                    
                    InetAddress ip = pkRcv.getAddress();
                    int port = pkRcv.getPort();
                    
                    String msg = "1,2,3, 4, 5, 6, 1,  2, 10, 3  ";
                    bytes = msg.getBytes();
                    pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
                    s.send(pkSend);
                    
                    bytes = new byte[1024];
                    pkRcv = new DatagramPacket(bytes, bytes.length);
                    s.receive(pkRcv);
                    
                    String res = new String(pkRcv.getData()).trim();
                    System.out.println(res);
                    if("1,10".equals(res)) {
                        msg = "OK";
                    } else {
                        msg = "NOT OK";
                    }
                    bytes = msg.getBytes();
                    pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
                    s.send(pkSend);
                } catch (SocketException ex) {
                    Logger.getLogger(ServerData.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ServerData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        new ServerData().start();
    }
}

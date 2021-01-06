package UDP.Object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerObject extends Thread {
    public void run() {
        try {
            DatagramSocket s = new DatagramSocket(8000);
            DatagramPacket pkSend;
            DatagramPacket pkRcv;
            ByteArrayOutputStream baos;
            ByteArrayInputStream bais;
            ObjectOutputStream oos;
            ObjectInputStream ois;
            byte[] bytes;
            while(true) {
                try {
                    bytes = new byte[1024];
                    pkRcv = new DatagramPacket(bytes, bytes.length);
                    s.receive(pkRcv);
                    bais = new ByteArrayInputStream(pkRcv.getData());
                    ois = new ObjectInputStream(bais);
                    System.out.println((String) ois.readObject());
                    System.out.println(new String(pkRcv.getData()).trim().length());
                    
                    InetAddress ip = pkRcv.getAddress();
                    int port = pkRcv.getPort();
                    
                    String msg = "1,2,3, 4, 5, 6, 1,  2, 10, 3  ";
                    baos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(baos);
                    oos.writeObject(msg);
                    bytes = baos.toByteArray();
                    System.out.println(new String(bytes).length());
                    pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
                    s.send(pkSend);
                    
                    bytes = new byte[1024];
                    pkRcv = new DatagramPacket(bytes, bytes.length);
                    s.receive(pkRcv);
                    bais = new ByteArrayInputStream(pkRcv.getData());
                    ois = new ObjectInputStream(bais);
                    
                    String res = (String) ois.readObject();
                    System.out.println(res.trim());
                    if("1,10".equals(res.trim())) {
                        msg = "OK";
                    } else {
                        msg = "NOT OK";
                    }
                    baos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(baos);
                    oos.writeObject(msg);
                    bytes = baos.toByteArray();
                    pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
                    s.send(pkSend);
                    
                    bytes = new byte[1024];
                    pkRcv = new DatagramPacket(bytes, bytes.length);
                    s.receive(pkRcv);
                    bais = new ByteArrayInputStream(pkRcv.getData());
                    ois = new ObjectInputStream(bais);
                    Student t = (Student) ois.readObject();
                    System.out.println(t.getTen());
                } catch (SocketException ex) {
                    Logger.getLogger(ServerObject.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ServerObject.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ServerObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        new ServerObject().start();
    }
}

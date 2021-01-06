package UDP.Data;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientData {

    public static String MINMAX(int a[]) {
        Arrays.sort(a);
        return String.format("%d,%d", a[0], a[a.length - 1]);
    }

    public static void main(String args[]) {
        DatagramSocket s;
        DatagramPacket pkSend;
        DatagramPacket pkRcv;
        byte[] bytes;

        try {
            s = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            int port = 8000;

            bytes = "B17DCCN510".getBytes();
            pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
            s.send(pkSend);

            bytes = new byte[1024];
            pkRcv = new DatagramPacket(bytes, bytes.length);
            s.receive(pkRcv);
            System.out.println(new String(pkRcv.getData()));
            
            String t[] = new String(pkRcv.getData()).split(",");
            int arr[] = new int[t.length];
            for(int i = 0; i < t.length; i++) {
                arr[i] = Integer.parseInt(t[i].trim());
            }
            
            bytes = MINMAX(arr).getBytes();
            pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
            s.send(pkSend);
            
            bytes = new byte[1024];
            pkRcv = new DatagramPacket(bytes, bytes.length);
            s.receive(pkRcv);
            System.out.println(new String(pkRcv.getData()));
        } catch (SocketException ex) {
            Logger.getLogger(ServerData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

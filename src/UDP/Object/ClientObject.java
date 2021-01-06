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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientObject {

    public static String MINMAX(int a[]) {
        Arrays.sort(a);
        return String.format("%d,%d", a[0], a[a.length - 1]);
    }

    public static void main(String args[]) {
        DatagramSocket s;
        DatagramPacket pkSend;
        DatagramPacket pkRcv;
        ByteArrayOutputStream baos;
        ByteArrayInputStream bais;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        byte[] bytes;

        try {
            s = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            int port = 8000;

            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject("B17DCCN510");
            bytes = baos.toByteArray();
            pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
            s.send(pkSend);

            bytes = new byte[1024];
            pkRcv = new DatagramPacket(bytes, bytes.length);
            s.receive(pkRcv);
            bais = new ByteArrayInputStream(pkRcv.getData());
            ois = new ObjectInputStream(bais);
            String msg = (String) ois.readObject();
            System.out.println(msg);
            
            String t[] = msg.split(",");
            int arr[] = new int[t.length];
            for (int i = 0; i < t.length; i++) {
                arr[i] = Integer.parseInt(t[i].trim());
            }

            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(MINMAX(arr));
            bytes = baos.toByteArray();
            pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
            s.send(pkSend);

            bytes = new byte[1024];
            pkRcv = new DatagramPacket(bytes, bytes.length);
            s.receive(pkRcv);
            bais = new ByteArrayInputStream(pkRcv.getData());
            ois = new ObjectInputStream(bais);
            msg = (String) ois.readObject();
            System.out.println(msg);
            
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(new Student("a",1));
            bytes = baos.toByteArray();
            pkSend = new DatagramPacket(bytes, bytes.length, ip, port);
            s.send(pkSend);
        } catch (SocketException ex) {
            Logger.getLogger(ClientObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

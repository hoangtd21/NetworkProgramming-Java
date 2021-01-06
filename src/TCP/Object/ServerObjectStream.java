package TCP.Object;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerObjectStream extends Thread {

    ServerSocket ss;
    ObjectInputStream is;
    ObjectOutputStream os;

    public ServerObjectStream() throws IOException {
        ss = new ServerSocket(1000);
    }

    public void run() {
        while (true) {
            try {
                Socket s = ss.accept();
                is = new ObjectInputStream(s.getInputStream());
                
                String rcv = (String) is.readObject();
                System.out.println(s.getInetAddress().getHostAddress() + ": "+rcv);
                
                os = new ObjectOutputStream(s.getOutputStream());
                os.writeObject("1,2,3, 4, 5, 6, 1,  2, 10, 3  ");

                String res = (String) is.readObject();
                System.out.println(res);
                if("1,10".equals(res)) {
                    os.writeObject("OK");
                } else {
                    os.writeObject("NOT OK");
                }
                Student t = (Student) is.readObject();
                System.out.println(t.getTen());
            } catch (IOException ex) {
                Logger.getLogger(ServerObjectStream.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerObjectStream.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerObjectStream().start();
    }
}

package TCP.Data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerDataStream extends Thread {

    ServerSocket ss;

    public ServerDataStream() throws IOException {
        ss = new ServerSocket(1000);
    }

    public void run() {
        while (true) {
            try {
                Socket s = ss.accept();
                DataInputStream is = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                DataOutputStream os = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                
                String rcv = is.readUTF();
                System.out.println(s.getInetAddress().getHostAddress() + ": "+rcv);
                
                os.writeUTF("1,2,3, 4, 5, 6, 1,  2, 10, 3  ");
                os.flush();

                String res = is.readUTF();
                System.out.println(res);
                if("1,10".equals(res)) {
                    os.writeUTF("OK");
                } else {
                    os.writeUTF("NOT OK");
                }
                os.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerDataStream.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerDataStream().start();
    }
}


package TCP.Byte;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerByteStream extends Thread {

    ServerSocket ss;

    public ServerByteStream() throws IOException {
        ss = new ServerSocket(1000);
    }

    public void run() {
        while (true) {
            try {
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                String rcv = "";
                byte[] bytes = new byte[1024];
                is.read(bytes);
                rcv = new String(bytes);
                System.out.println(s.getInetAddress().getHostAddress() + ": "+rcv);
                
                os.write("1,2,3, 4, 5, 6, 1,  2, 10, 3  ".getBytes());
                os.flush();
                
                bytes = new byte[1024];
                is.read(bytes);
                String res = new String(bytes).trim();
                System.out.println(res);
                if("1,10".equals(res)) {
                    os.write("OK".getBytes());
                    os.flush();
                } else {
                    os.write("NOT OK".getBytes());
                    os.flush();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerByteStream.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerByteStream().start();
    }
}

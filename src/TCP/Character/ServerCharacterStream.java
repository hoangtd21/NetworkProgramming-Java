package TCP.Character;

import TCP.Byte.ServerByteStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerCharacterStream extends Thread {

    ServerSocket ss;

    public ServerCharacterStream() throws IOException {
        ss = new ServerSocket(8000);
    }

    public void run() {
        BufferedReader is;
        BufferedWriter os;
        while (true) {
            try {
                Socket s = ss.accept();
                is = new BufferedReader(new InputStreamReader(s.getInputStream()));
                os = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                
                String rcv = is.readLine();
                System.out.println(s.getInetAddress().getHostAddress() + ": "+rcv);
                
                os.write("1,2,3, 4, 5, 6, 1,  2, 10, 3  ");
                os.newLine();
                os.flush();
                
                String res = is.readLine();
                System.out.println(res);
                if("1,10".equals(res)) {
                    os.write("OK");
                } else {
                    os.write("NOT OK");
                }
                os.newLine();
                os.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerByteStream.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerCharacterStream().start();
        
    }
}

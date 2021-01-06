package TCP.Data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ClientDataStream {
    public static String MINMAX(int a[]) {
        Arrays.sort(a);
        return String.format("%d,%d", a[0], a[a.length-1]);
    }

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 1000);
        DataInputStream is = new DataInputStream(s.getInputStream());
        DataOutputStream os = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));

        os.writeUTF("B17DCCN510");
        os.flush();

        String t[] = is.readUTF().split(",");
        int arr[] = new int[t.length];
        for(int i = 0; i < t.length; i++) {
            arr[i] = Integer.parseInt(t[i].trim());
        }
        
        os.writeUTF(MINMAX(arr));
        os.flush();
        
        System.out.println(is.readUTF());
        
        s.close();
    }
}

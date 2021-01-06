package TCP.Byte;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientByteStream {
    public static String MINMAX(int a[]) {
        Arrays.sort(a);
        return String.format("%d,%d", a[0], a[a.length-1]);
    }

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 1000);
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        os.write("B17DCCN510".getBytes());
        os.flush();
        
        byte[] bytes = new byte[1024];
        is.read(bytes);
        String t[] = new String(bytes).split(",");
        int arr[] = new int[t.length];
        for(int i = 0; i < t.length; i++) {
            arr[i] = Integer.parseInt(t[i].trim());
        }
        
        os.write(MINMAX(arr).getBytes());
        os.flush();
        
        bytes = new byte[1024];
        is.read(bytes);
        System.out.println(new String(bytes));
        
        s.close();
    }
}

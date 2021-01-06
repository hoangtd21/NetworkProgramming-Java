package TCP.Object;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientObjectStream {
    public static String MINMAX(int a[]) {
        Arrays.sort(a);
        return String.format("%d,%d", a[0], a[a.length-1]);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("localhost", 1000);
        
        ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
        os.writeObject("B17DCCN510");

        ObjectInputStream is = new ObjectInputStream(s.getInputStream());
        String x = (String) is.readObject();
        String t[] = x.split(",");
        int arr[] = new int[t.length];
        for(int i = 0; i < t.length; i++) {
            arr[i] = Integer.parseInt(t[i].trim());
        }
        
        os.writeObject(MINMAX(arr));
        System.out.println((String)is.readObject());
        
        os.writeObject(new Student("a",1));
        s.close();
    }
}

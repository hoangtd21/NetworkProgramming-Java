package TCP.Character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ClientCharacterStream {

    public static String MINMAX(int a[]) {
        Arrays.sort(a);
        return String.format("%d,%d", a[0], a[a.length - 1]);
    }

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8000);
        BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        os.write("B17DCCN510");
        os.newLine();
        os.flush();

        String rcv = is.readLine();
        System.out.println(rcv);
        String t[] = rcv.split(",");
        int arr[] = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            arr[i] = Integer.parseInt(t[i].trim());
        }

        os.write(MINMAX(arr));
        os.newLine();
        os.flush();

        String res = is.readLine();
        System.out.println(res);

        s.close();
    }
}

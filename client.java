import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client Started..");
        Socket s = new Socket("127.0.0.1", 2222);
        System.out.println("Server connected..");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

                String msg = sc.nextLine();
                oos.writeObject(msg);
                Object ck = ois.readObject();
                System.out.println((String)ck);

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}
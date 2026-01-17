import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client Started..");
        System.out.println("Client connected..");
        while (true) {
            Socket s = new Socket("127.0.0.1", 22222);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            oos.writeObject(msg);

            try {
                Object fromServer = ois.readObject();
                System.out.println("From Server: " + (String) fromServer);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}

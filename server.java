import java.io.*;
import java.net.*;

public class server {

    public static void main(String[] args) throws IOException {
        System.out.println("server started..");
        ServerSocket server1 = new ServerSocket(22222);

        while (true) {
            Socket s = server1.accept();
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

            try {
                Object msg = ois.readObject();
                System.out.println("From Client: " + msg);
                String serverMsg = (String) msg;
                serverMsg = serverMsg.toUpperCase();
                oos.writeObject(serverMsg);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}

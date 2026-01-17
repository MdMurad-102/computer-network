import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException
    {
          ServerSocket serverSocket = new ServerSocket(2222);
          System.out.println("Server started..");

          while (true) {
            Socket s = serverSocket.accept() ; 
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            try {
                Object msg = ois.readObject();
                System.out.println("msg rcv: "+(String)msg);
                String rtn =(String)msg ; 
                rtn = rtn.toUpperCase()+"server response";
                oos.writeObject(rtn);
            } catch (Exception e) {
                System.out.println(e);
            }

          }

       }
}
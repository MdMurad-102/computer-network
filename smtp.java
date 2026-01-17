import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.util.Base64;

public class smtp {
   public static void send(String msg) throws IOException{
        wrt.write(msg);
        wrt.flush();
        System.out.println("C: "+msg.trim());
   }
   public static void rcv() throws IOException{
      String rd = red.readLine();
      System.out.println(rd);
   }
   

   private static BufferedReader red ;
   private static BufferedWriter wrt ;
  public static void main(String[] args){
    String user = "s2210376102@ru.ac.bd" ; 
    String pass = "nkmikthktiqfhupk";

    try {
        SSLSocket ssl = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com" , 465);
        red  = new BufferedReader(new InputStreamReader(ssl.getInputStream()));
        wrt = new BufferedWriter(new OutputStreamWriter(ssl.getOutputStream())) ; 
        
        rcv(); // Server greeting
        
        send("EHLO smtp.gmail.com\r\n");
        for(int i=0; i<9; i++) rcv();
        
        String username = Base64.getEncoder().encodeToString(user.getBytes());
        String password = Base64.getEncoder().encodeToString(pass.getBytes());
        
        send("AUTH LOGIN\r\n");
        rcv();
        send(username + "\r\n");
        rcv();
        send(password + "\r\n");
        rcv();
        
        send("MAIL FROM:<" + user + ">\r\n");
        rcv();
        send("RCPT TO:<" + user + ">\r\n");
        rcv();
        
        send("DATA\r\n");
        rcv();
        send("FROM: " + user + "\r\n");
        send("TO: " + user + "\r\n");
        send("Subject: Test Email\r\n");
        send("\r\n");
        send("THIS IS A TEST EMAIL\r\n");
        send(".\r\n");
        rcv();
        
        send("QUIT\r\n");
        rcv();
        
        ssl.close();
        System.out.println("Email sent!");
        
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}

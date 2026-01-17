//  send("EHLO smtp.gmail.com\r\n");              -->        9 lines
//     send("AUTH LOGIN\r\n");                      -->      1 line
//     send(username + "\r\n");                    -->      1 line
//     send(password + "\r\n");                   -->      1 line
//     send("MAIL FROM:<asif@ru.ac.bd>\r\n");       -->        1 line
//     send("RCPT TO:<asifzaman3180@gmail.com>\r\n");  -->      1 line
//     send("DATA\r\n");                -->      1 line
//     send("FROM: asif@ru.ac.bd\r\n");                            
//     send("TO: asifzaman3180@gmail.com\r\n");                    
//     send("Subject: Email test  "\r\n");
//     send("THIS IS A TEST EMAIL. THANK YOU\r\n");
//     send(".\r\n");                   -->      1 line
//     send("QUIT\r\n");

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Base64;

public class GmailSSLSMTP {

    public static void send(String message) throws IOException {
        writer.write(message);
        writer.flush();
        System.out.println("C: " + message.trim());
    }

    public static void readResponse() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("S: " + line);
            if (!line.startsWith("250-") && !line.startsWith("220-")) {
                break;
            }
        }
    }

    private static BufferedReader reader;
    private static BufferedWriter writer;

    public static void main(String[] args) {
        String username = "email";
        String appPassword = "password";

        try {
            SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
            reader = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            writer = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));
            readResponse();

            send("EHLO smtp.gmail.com\r\n");
            readResponse();

            send("AUTH LOGIN\r\n");
            readResponse();

            send(Base64.getEncoder().encodeToString(username.getBytes()) + "\r\n");
            readResponse();

            send(Base64.getEncoder().encodeToString(appPassword.getBytes()) + "\r\n");
            readResponse();

            send("MAIL FROM:<s2210376102@ru.ac.bd>\r\n");
            readResponse();

            send("RCPT TO:<s2210376102@ru.ac.bd\r\n");
            readResponse();

            send("DATA\r\n");
            readResponse();

            send("FROM:Md.Murad_102 <s2210376102@ru.ac.bd>\r\n");
            send("TO: s2210376102@ru.ac.bd\r\n");
            send("Subject: Email Test 102\r\n");
            send("\r\n");
            send("THIS IS A TEST EMAIL.\r\n");
            send("THANK YOU.\r\n");

            send(".\r\n");
            readResponse();

            send("QUIT\r\n");
            readResponse();

            s.close();
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
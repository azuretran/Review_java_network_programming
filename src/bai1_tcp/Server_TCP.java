package bai1_tcp;
import Function.Main_function;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
public class Server_TCP extends Main_function{
    
    /* declare variables */
   Socket client;
    DataInputStream din;
    DataOutputStream dout;
    String resulten;
       public static final char CHAR_55 = 55;
  
    public static void main(String argv[]) throws Exception
    {
        ServerSocket server=new ServerSocket(8087);
        while (true) {            
            Socket serverSocket=server.accept();
            DataInputStream din=new DataInputStream(serverSocket.getInputStream());
            DataOutputStream dout=new DataOutputStream(serverSocket.getOutputStream());
             int number=din.readInt();
              int base=din.readInt();
   
              String s=convertNumber(number, base);
              System.out.println(s);
              dout.writeUTF(s);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI6_TCP;

import BAI3_TCP.*;
import BAI2_TCP.*;
import static BAI6_TCP.Client_TCP_b2.nhap;
import Function.Main_function;
import static Function.Main_function.convertNumber;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author azure Tran
 */
public class Server_TCP_b2 extends Main_function{
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
             int i,m;
             String resutString="";
             System.out.println(number);
             int[] array= new int[number]; 
          for(i=0 ; i<number ; i++){

              m=din.readInt(); array[i]=m;
              
        }
              for(i=0 ; i<number ; i++){
            if(countElement(array, number, array[i])==1) 
          
            resutString+=" "+Integer.toString(array[i]);
   }
              dout.writeUTF(resutString);
        }
    }
}

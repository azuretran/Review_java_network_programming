/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI3_TCP;

import BAI2_TCP.*;
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
             // phân tích số nguyên dương n
          List<Integer> listNumbers = phanTichSoNguyen(number);
        // in kết quả ra màn hình
          int size = listNumbers.size();
          String result="";
          for (int i = 0; i < size - 1; i++) {
           
            result+=Integer.toString(listNumbers.get(i))+"x";
        }
           
             result+=listNumbers.get(size - 1);
              dout.writeUTF(result);
        }
    }
}

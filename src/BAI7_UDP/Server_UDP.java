/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI7_UDP;

import BAI5_UDP.*;
import BAI4_UDP.*;
import BAI1_UDP.*;
import Function.Main_function;
import static bai1_tcp.Server_TCP.CHAR_55;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author azure Tran
 */
public class Server_UDP extends Main_function {
     public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
    public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận
   
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ds = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");
 
            while (true) { // Tạo gói tin nhận
                DatagramPacket number = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(number); // Chờ nhận gói tin gởi đến
                   String number1 = new String(number.getData(), 0, number.getLength());
                System.out.println("Received: " + number1);
              int length=Integer.parseInt(number1);
                
             //received array[i] value
                
                int i;
             String resutString="";
             int[] array= new int[length]; 
          for(i=0 ; i<length ; i++){
                //receive data
                DatagramPacket m = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(m); // Chờ nhận gói tin gởi đến
                   String m1 = new String(m.getData(), 0, m.getLength());
             //convert from string to int           
              array[i]=Integer.parseInt(m1);
              
        }
           for(i=0 ; i<length ; i++){
            if((countElement(array, length, array[i])==2)&&(resutString.contains(Integer.toString(array[i]))==false)) 
          
            resutString+=" "+Integer.toString(array[i]);
         }
                  
                           // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                DatagramPacket outsending = new DatagramPacket(resutString.getBytes(), resutString.length(),
                        number.getAddress(), number.getPort());
                
                ds.send(outsending);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}

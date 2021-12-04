/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI14_UDP;

import BAI13_UDP.*;
import BAI12_UDP.*;
import BAI11_UDP.*;
import BAI4_UDP.*;
import BAI1_UDP.*;
import Function.Main_function;
import static Function.Main_function.phanTichSoNguyen;
import static bai1_tcp.Server_TCP.CHAR_55;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

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
                //list number isprimenumber<number
                String result="so nguyen to dau tien:"+"\n";
               
        for(int i = 1; i <= Integer.parseInt(number1); i++) {
           
                if(isPrimeNumber(i)) result+=i+" ";
           
        }
        result+="\n";
         int n1=0,n2=1,n3,i;
         String result_uocso="so FIbo dau tien: \n ";result_uocso+=0+" "+1+" ";
         for(i=2;i<Integer.parseInt(number1);++i)//loop starts from 2 because 0 and 1 are already printed    
 {    
  n3=n1+n2;    
  System.out.print(" "+n3);    
    result_uocso+=" "+n3;    
  n1=n2;    
  n2=n3;    
 }    
        result+=result_uocso;
                           // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                DatagramPacket outsending = new DatagramPacket(result.getBytes(), result.length(),
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

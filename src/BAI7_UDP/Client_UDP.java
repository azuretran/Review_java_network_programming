/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI7_UDP;

import BAI5_UDP.*;
import BAI4_UDP.*;
import BAI1_UDP.*;
import static BAI6_TCP.Client_TCP_b2.nhap;
import Function.Main_function;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author azure Tran
 */
public class Client_UDP {
     public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
    public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận
  public static int nhap(){
         Scanner scanner=new Scanner(System.in);
     boolean check= false;
          int flag_1=1,flag_2=1;
         int number=0;
          while(!check){
            System.out.print(" ");
            if(flag_1!=0){
            try{
              
                number= scanner.nextInt();
                flag_1=0;
            }catch(Exception e){
                System.out.println("you need to input a normal number...");
                scanner.nextLine();
            }
            }
           
             if(flag_1==0) check=true;
        }
          return number;
    
    }
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(); // Tạo DatagramSocket
            System.out.println("Client started ");
 
            InetAddress server = InetAddress.getByName(SERVER_IP);
            while (true) {
                
                //send length of array
               System.out.println("input length of array:");
                 int number=nhap();
                 int i;
                 
                byte[] data = Integer.toString(number).getBytes(); // Đổi chuỗi ra mảng bytes
               DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
                ds.send(dp); // Send gói tin sang Echo Server
                //send a[i]
                  int[] array= new int[number]; 
          for(i=0 ; i<number ; i++){
             System.out.println("input arr[ " +(i+1)+" ]"); 
             array[i]= nhap();  
             byte[] data1 = Integer.toString(array[i]).getBytes(); // Đổi chuỗi ra mảng bytes
               DatagramPacket dp1 = new DatagramPacket(data1, data1.length, server, SERVER_PORT);
                ds.send(dp1); // Send gói tin sang Echo Server
        }
                
                
                
                
                
                // Gói tin nhận
                DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming); // Chờ nhận dữ liệu từ EchoServer gởi về
 
                // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                System.out.println("Kết quả: " + new String(incoming.getData(), 0, incoming.getLength()));
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}

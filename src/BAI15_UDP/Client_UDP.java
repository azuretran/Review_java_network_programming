/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI15_UDP;

import BAI7_UDP.*;
import BAI5_UDP.*;
import BAI4_UDP.*;
import BAI1_UDP.*;
import static BAI6_TCP.Client_TCP_b2.nhap;
import Function.Main_function.*;
import static Function.Main_function.findMaxMT;
import static Function.Main_function.inMT;
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
          public  static int number=0,base=0;
  public static void nhap_mattrix(){
        Scanner scanner=new Scanner(System.in);
                   boolean check= false;
          int flag_1=1,flag_2=1;
    
          while(!check){
            System.out.print(" ");
            if(flag_1!=0){
            try{
                System.out.println("input row:");
                number= scanner.nextInt();
                if(number<0) flag_1=1;
                else
                flag_1=0;
                
 
                // Tạo gói tin gởi
            
            }catch(Exception e){
                System.out.println("you need to input a normal row>0...");
                scanner.nextLine();
            }
            }
            if(flag_2!=0){
             try{
                System.out.println("input column:");
                base= scanner.nextInt();
                if(base<0) flag_2=1;
                else
                flag_2=0;
                  
            }catch(Exception e){
                System.out.println("you need to input a normal column >0...");
                scanner.nextLine();
            }
            }
             if(flag_1==0&&flag_2==0) check=true;
          
          }
    }
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(); // Tạo DatagramSocket
            System.out.println("Client started ");
 
            InetAddress server = InetAddress.getByName(SERVER_IP);
            while (true) {
                
                //send length of array
               System.out.println("input information for array:");
                nhap_mattrix();
                
                 
                byte[] data = Integer.toString(number).getBytes(); // Đổi chuỗi ra mảng bytes
               DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
                ds.send(dp); // Send gói tin row sang Echo Server
                  byte[] data1 = Integer.toString(base).getBytes(); // Đổi chuỗi ra mảng bytes
               DatagramPacket dp1 = new DatagramPacket(data1, data1.length, server, SERVER_PORT);
                ds.send(dp1); // Send gói tin column sang Echo Server
                //send a[i]
                int [][] A= new int[number][base]; 
                    int i,j;
      for(i=0 ; i<number ; i++){
         for(j=0 ; j<base ; j++){
            System.out.println("Nhap phan tu thu A["+(i+1)+"]["+(j+1)+"]= "); 
            A[i][j]= nhap();
               byte[] data11 = Integer.toString(A[i][j]).getBytes(); // Đổi chuỗi ra mảng bytes
               DatagramPacket dp11 = new DatagramPacket(data11, data11.length, server, SERVER_PORT);
                ds.send(dp11); // Send gói tin sang Echo Server
         }
 
      }
             System.out.println("Ma tran nhap vao: "); 
      inMT(A, number, base);
     System.out.println("\n");
                
                
                
                
                
                // Gói tin nhận 1
                DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming); // Chờ nhận dữ liệu từ EchoServer gởi về
                // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                System.out.println("Tìm phần tử lớn nhất của ma trận cùng chỉ số của số đó : \n " + new String(incoming.getData(), 0, incoming.getLength()));
                  // Gói tin nhận 2
                DatagramPacket incoming1 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming1); // Chờ nhận dữ liệu từ EchoServer gởi về
                // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                System.out.println("Tìm và in ra các phần tử là số nguyên tố của ma trận (các phần tử không nguyên tố thì thay bằng số 0): \n " + new String(incoming1.getData(), 0, incoming1.getLength()));
                  // Gói tin nhận 3
                DatagramPacket incoming2 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming2); // Chờ nhận dữ liệu từ EchoServer gởi về
                // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                System.out.println("Sắp xếp tất cả các cột của ma trận theo thứ tự tăng dần và in kết quả ra màn hình:\n " + new String(incoming2.getData(), 0, incoming2.getLength()));
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

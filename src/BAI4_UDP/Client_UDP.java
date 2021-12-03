/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI4_UDP;

import BAI1_UDP.*;
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
 
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(); // Tạo DatagramSocket
            System.out.println("Client started ");
 
            InetAddress server = InetAddress.getByName(SERVER_IP);
            while (true) {
                /*System.out.println("Enter your message: ");
                
                InputStreamReader isr = new InputStreamReader(System.in); // Nhập
                BufferedReader br = new BufferedReader(isr); // một chuỗi
                String theString = br.readLine(); // từ bàn phím*/
                  Scanner scanner=new Scanner(System.in);
                   boolean check= false;
          int flag_1=1,flag_2=1;
         int number=0,base=0;
          while(!check){
            System.out.print(" ");
            if(flag_1!=0){
            try{
                System.out.println("input number:");
                number= scanner.nextInt();
                flag_1=0;
                
 
                // Tạo gói tin gởi
            
            }catch(Exception e){
                System.out.println("you need to input a normal number...");
                scanner.nextLine();
            }
            }
           
             if(flag_1==0) check=true;
        }
                byte[] data = Integer.toString(number).getBytes(); // Đổi chuỗi ra mảng bytes
               DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
                ds.send(dp); // Send gói tin sang Echo Server
                
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

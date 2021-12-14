/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI24_UDP;

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
    public static DatagramPacket sendata(String valueString, InetAddress server){
           byte[] data =valueString.getBytes(); // Đổi chuỗi ra mảng bytes
              DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
              return  dp;
    }
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = null;
        try 
        {
            ds = new DatagramSocket(); // Tạo DatagramSocket
            System.out.println("Client started ");
 
            InetAddress server = InetAddress.getByName(SERVER_IP);
            while (true) {
            
                  Scanner scanner=new Scanner(System.in);
              String username,password;
                // input username        
                System.out.println("input username: :");
                username= scanner.nextLine();
     
               DatagramPacket dp = sendata(username, server);
                ds.send(dp); // Send gói tin sang Echo Server
                    //input password
                 System.out.println("input password: :");
                password= scanner.nextLine();
               
          
            
               DatagramPacket dp1 = sendata(password, server);
                ds.send(dp1); // Send gói tin sang Echo Server
                
                // Gói tin nhận login thành công
                DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back=new String(incoming.getData(), 0, incoming.getLength());
                // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                   // Gói tin nhận số kết quả
                DatagramPacket incoming7 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming7); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back7=new String(incoming7.getData(), 0, incoming7.getLength());
                   int i;
                   String s="";
                    for(i=0;i<Integer.parseInt(result_back7);i++){
                           // Gói tin nhận dữ liệu mã sinh viên
                DatagramPacket incoming1 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming1); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back1=new String(incoming1.getData(), 0, incoming1.getLength());
                    // Gói tin nhận dữ liệu họ lót
                DatagramPacket incoming2 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming2); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back2=new String(incoming2.getData(), 0, incoming2.getLength());
                    // Gói tin nhận dữ liệu tên
                DatagramPacket incoming3 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming3); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back3=new String(incoming3.getData(), 0, incoming3.getLength());
                    // Gói tin nhận dữ liệu mã lớp
                DatagramPacket incoming4 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming4); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back4=new String(incoming4.getData(), 0, incoming4.getLength());
                    // Gói tin nhận dữ liệu điểm trung bình
                DatagramPacket incoming5 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming5); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back5=new String(incoming5.getData(), 0, incoming5.getLength());
                
                    // Gói tin nhận dữ liệu kết quả
                DatagramPacket incoming6 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming6); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back6=new String(incoming6.getData(), 0, incoming6.getLength());
                s+=result_back1+"\t"+result_back2+"\t"+result_back3+"\t"+result_back4+"\t"+result_back5+"\t"+result_back6+"\n";
                    }
              
                
                
                
                
                
                if(Integer.parseInt(result_back)==-1){
                    System.out.println("login successfully");
                    
                         System.out.println("Mã SV"+"\t"+"Họ Lót"+"\t"+"Tên"+"\t"+"Mã Lớp"+"\t"+"\t"+"Điểm TB"+"\t"+"Kết Quả");
                                        System.out.println(s);
                  
                                       
                }
                else{
                    System.out.println("fail login please try again , may be wrong username or password");
                }
                
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI25_UDP;

import BAI24_UDP.*;
import Function.DBConection;
import Function.Main_function;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static void main(String[] args) throws SQLException {
      /*  DatagramSocket ds = null;
        try {
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
                // Gói tin nhận
                DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming); // Chờ nhận dữ liệu từ EchoServer gởi về
                String result_back=new String(incoming.getData(), 0, incoming.getLength());
                // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                if(Integer.parseInt(result_back)==-1){
                    System.out.println("login successfully");
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
        }*/
                        Connection connect = DBConection.getConnection();

         String name = "";
        String reply = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên của bạn để tham gia phòng chat và nhập logout nếu muốn thoát  : ");
        reply = sc.nextLine();
        name = reply;
       
        try (Socket socket = new Socket("localhost", 1406)) {
            PrintWriter cout = new PrintWriter(socket.getOutputStream(), true);

            ThreadClient threadClient = new ThreadClient(socket);
            new Thread(threadClient).start(); // thread bắt đầu nhận tin nhắn 

            cout.println(reply + ": Đã tham gia .");
         

                
            do {
                String message = (name + " : ");
                reply = sc.nextLine();
                 
                if (reply.equals("logout")) {
                    cout.println("logout");
                    break;
                }
                cout.println(message + reply);
            } while (!reply.equals("logout"));
               String query ="insert into CHATMULTI values(?,?)";
                    PreparedStatement ps = connect.prepareStatement(query);
                    ps.setString(1, name);
                    ps.setString(2, reply);
                    ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
    }
}

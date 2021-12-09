/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI25_UDP;


import BAI24_UDP.*;
import Function.DBConection;
import Function.Main_function;
import static Function.Main_function.phanTichSoNguyen;
import static bai1_tcp.Server_TCP.CHAR_55;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author azure Tran
 */
public class Server_UDP extends Main_function {
    // public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
    //public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận
 
    public static void main(String[] args) throws SQLException {
       /* DatagramSocket ds = null;
                Connection connect = DBConection.getConnection();

        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ds = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");
 
            while (true) { // Tạo gói tin nhận
                DatagramPacket username = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(username); // Chờ nhận gói tin gởi đến
                   String username1 = new String(username.getData(), 0, username.getLength());
                System.out.println("Received: " + username1);
            //recieve password
                   DatagramPacket password = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(password); // Chờ nhận gói tin gởi đến
                   String password1 = new String(password.getData(), 0, password.getLength());
                System.out.println("Received: " + password1);
                
                    String query = "	  SELECT *from dbo.login  where username=? and password=?";

                    PreparedStatement ps = connect.prepareStatement(query);
                    ps.setString(1, username1);
                    ps.setString(2, password1);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                     
                         
                           // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                    DatagramPacket outsending = new DatagramPacket("-1".getBytes(), "-1".length(),
                        username.getAddress(), username.getPort());
                
                ds.send(outsending);

                    } else {
                           
                           // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                    DatagramPacket outsending = new DatagramPacket("-2".getBytes(), "-2".length(),
                        username.getAddress(), username.getPort());
                      ds.send(outsending);
                    }
              
              
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }*/
            ArrayList<Socket> clients = new ArrayList<>();
        // tạo hashmap danh sách máy client
        HashMap<Socket, String> clientNameList = new HashMap<Socket, String>();
        try (ServerSocket serversocket = new ServerSocket(1406)) {
            System.out.println("Server bắt đầu chạy...");
            while (true) {
                Socket socket = serversocket.accept();
                clients.add(socket);
                ThreadServer ThreadServer = new ThreadServer(socket, clients, clientNameList);
                ThreadServer.start();
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}

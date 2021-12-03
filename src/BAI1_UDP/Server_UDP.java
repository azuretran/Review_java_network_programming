/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI1_UDP;

import static bai1_tcp.Server_TCP.CHAR_55;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author azure Tran
 */
public class Server_UDP {
     public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
    public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận
    public static String convertNumber(int n, int b) {
        if (n < 0 || b < 1 || b > 24) {
            return "";
        }
         
        StringBuilder sb = new StringBuilder();
        int m;
        int remainder = n;
         
        while (remainder > 0) {
            if (b > 10) {
                m = remainder % b;
                if (m >= 10) {
                    sb.append((char) (CHAR_55 + m));
                } else {
                    sb.append(m);
                }
            } else {
                sb.append(remainder % b);
            }
            remainder = remainder / b;
        }
        return sb.reverse().toString();
    }
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
                   DatagramPacket base = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(base); // Chờ nhận gói tin gởi đến
                // Lấy dữ liệu khỏi gói tin nhận
             
                 String base1 = new String(base.getData(), 0, base.getLength());
                System.out.println("Received: " + base1);
                
                String result=convertNumber(Integer.parseInt(number1),Integer.parseInt(base1));                // Tạo gói tin gởi chứa dữ liệu vừa nhận được
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

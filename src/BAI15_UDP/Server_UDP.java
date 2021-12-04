/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI15_UDP;

import static BAI15_UDP.Client_UDP.SERVER_PORT;
import static BAI15_UDP.Client_UDP.base;
import static BAI15_UDP.Client_UDP.number;
import BAI7_UDP.*;
import BAI5_UDP.*;
import BAI4_UDP.*;
import BAI1_UDP.*;
import static BAI6_TCP.Client_TCP_b2.nhap;
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
                //receive row
                DatagramPacket number = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(number); // Chờ nhận gói tin gởi đến
                   String number1 = new String(number.getData(), 0, number.getLength());
                System.out.println("Received: " + number1);
                               
              int length=Integer.parseInt(number1);//row value
                //receive column
                DatagramPacket number_1 = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(number_1); // Chờ nhận gói tin gởi đến
                   String number_11 = new String(number_1.getData(), 0, number_1.getLength());
                System.out.println("Received: " + number1);
                               
              int length_1=Integer.parseInt(number_11);//column value
              
             //received array[i][j] value
              
             String resutString="",resultPrimeNumber="";
             String        sortMattrix="";
          
               int [][] A= new int[length][length_1]; 
                    int i,j;
            for(i=0 ; i<length ; i++){
         for(j=0 ; j<length_1 ; j++){
            //receive data
               DatagramPacket m = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(m); // Chờ nhận gói tin gởi đến
                   String m1 = new String(m.getData(), 0, m.getLength());
             //convert from string to int           
              A[i][j]=Integer.parseInt(m1);
       
         }
 
      }
              sortColum(A, length, length_1);
            //find the best value in mattrix
               for(i=0 ; i<length ; i++){
         for(j=0 ; j<length_1 ; j++){
                         if(A[i][j]==findMaxMT(A, length, length_1))  resutString=("\nPhan tu o hang "+i+" cot "+j+" dat Max: A["+i+"]["+j+"]= "+A[i][j]) +"\n";
                        if(isPrimeNumber(A[i][j]))
                        {
                              
            resultPrimeNumber+=(" "+A[i][j]);    
          }
                        else
            resultPrimeNumber+=(" "+0);
             
         }
         }
               
               for(i=0 ; i<length ; i++){
     sortMattrix+=("\n");
    for(j=0 ; j<length_1 ; j++) sortMattrix+=(" "+A[i][j]);
 
  }
                  
                  //send packet 1
                           // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                DatagramPacket outsending = new DatagramPacket(resutString.getBytes(), resutString.length(),
                        number.getAddress(), number.getPort());
                ds.send(outsending);
                //send packet 2
                  DatagramPacket outsending1 = new DatagramPacket(resultPrimeNumber.getBytes(), resultPrimeNumber.length(),
                        number.getAddress(), number.getPort());
                ds.send(outsending1);
                 //send packet 3
                  DatagramPacket outsending2 = new DatagramPacket(sortMattrix.getBytes(), sortMattrix.length(),
                        number.getAddress(), number.getPort());
                ds.send(outsending2);
                
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

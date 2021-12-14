/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI24_UDP;


import Function.DBConection;
import Function.Main_function;
import static Function.Main_function.phanTichSoNguyen;
import static bai1_tcp.Server_TCP.CHAR_55;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azure Tran
 */
public class Server_UDP extends Main_function {
     public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
    public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận
 
    public static void main(String[] args) throws SQLException {
        DatagramSocket ds = null;
                Connection connect = DBConection.getConnection();
 int dem=0;
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
                    //tạo mảng sinh viên
                    ArrayList<sinhvien> sinhvienlist=new ArrayList();
                    //tạo mảng điểm sinh viên
                      ArrayList<Diem> Diemlist=new ArrayList();

                    //nếu đăng nhập thành công
                    if (rs.next()) {
                     
                         
                         //Lấy thông tin của sinh viên 
                        
                    String query_list_sinhvien = "	  SELECT *from LTM_SINHVIEN";

                        Statement statement=connect.createStatement();
                 
                    ResultSet rs_list_sinhvien  =  statement.executeQuery(query_list_sinhvien);
                    sinhvien user;
                    while(rs_list_sinhvien.next()){
                        user=new sinhvien(rs_list_sinhvien.getString("msv"), rs_list_sinhvien.getString("ho_lot"), rs_list_sinhvien.getString("ten"), rs_list_sinhvien.getString("malop"));
                        sinhvienlist.add(user);
                    
                    }
                   
                    
                        //Lấy thông tin điểm của sinh viên
                    
                      String query_list_diem_sinhvien = "	  SELECT *from LTM_DIEM";

                        Statement statement1=connect.createStatement();
                 
                    ResultSet rs_list_diem_sinhvien  =  statement1.executeQuery(query_list_diem_sinhvien);
                        // Tạo gói tin gởi đăng nhập thành công
                    DatagramPacket outsending = new DatagramPacket("-1".getBytes(), "-1".length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending);
                    Diem user1;
                    while(rs_list_diem_sinhvien.next()){
                        user1=new Diem(rs_list_diem_sinhvien.getString("msv"),rs_list_diem_sinhvien.getFloat("diemtoan"),rs_list_diem_sinhvien.getFloat("diemvan"));
                        Diemlist.add(user1);
                    
                    }
                    String masv="",holot="",ten="",malop="",kq="";
                    float tb=0;
                   for (sinhvien sinhvienlist1 : sinhvienlist) {
                       dem++;
                   }
                       // Tạo gói tin gởi chứa dữ liệu vừa nhận được
                    DatagramPacket outsending7 = new DatagramPacket(Integer.toString(dem).getBytes(), Integer.toString(dem).length(),
                        username.getAddress(), username.getPort());
                      ds.send(outsending7);
                    //in thông tin sinh viên
                    System.out.println("Mã SV"+"\t"+"Họ Lót"+"\t"+"Tên"+"\t"+"Mã Lớp"+"\t"+"\t"+"Điểm TB"+"\t"+"Kết Quả");
                    for (sinhvien sinhvienlist1 : sinhvienlist) {
                         for (Diem Diemlist1 : Diemlist) {
                             if(Diemlist1.getMasv().equals(sinhvienlist1.getMasv())){
                                 if((Diemlist1.getDiemtoan()+Diemlist1.getDiemvan()/2)>=5)
                                 {
                                        System.out.println(sinhvienlist1.getMasv()+"\t"+sinhvienlist1.getHolot()+"\t"+
                                    sinhvienlist1.getTen()+"\t"+sinhvienlist1.getMalop()+"\t"+(Diemlist1.getDiemtoan()+Diemlist1.getDiemvan()/2)+"\t"+"D");
                                        masv=sinhvienlist1.getMasv();
                                        holot=sinhvienlist1.getHolot();
                                        ten=sinhvienlist1.getTen();malop=sinhvienlist1.getMalop();
                                        tb=Diemlist1.getDiemtoan()+Diemlist1.getDiemvan()/2;
                                        kq="D";
                                      
                                // Tạo gói tin gởi dữ liệu table
                // tạo gói tin nhận mã sinhvien
                    DatagramPacket outsending1 = new DatagramPacket(masv.getBytes(), masv.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending1);
                  // tạo gói tin nhận họ lót
                    DatagramPacket outsending2 = new DatagramPacket(holot.getBytes(), holot.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending2);
                    // tạo gói tin nhận tên
                    DatagramPacket outsending3 = new DatagramPacket(ten.getBytes(), ten.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending3);
                  // tạo gói tin nhận mã lớp
                    DatagramPacket outsending4 = new DatagramPacket(malop.getBytes(), malop.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending4);
                // tạo gói tin nhận mã điểm trung bình
                    DatagramPacket outsending5 = new DatagramPacket(Float.toString(tb).getBytes(), Float.toString(tb).length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending5);
                // tạo gói tin nhận kết quả
                    DatagramPacket outsending6 = new DatagramPacket(kq.getBytes(), kq.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending6);
                                 }
                         
                                 else{
                            System.out.println(sinhvienlist1.getMasv()+"\t"+sinhvienlist1.getHolot()+"\t"+
                                    sinhvienlist1.getTen()+"\t"+sinhvienlist1.getMalop()+"\t"+(Diemlist1.getDiemtoan()+Diemlist1.getDiemvan()/2)+"\t"+"R");
                              masv=sinhvienlist1.getMasv()+"\t";
                                        holot=sinhvienlist1.getHolot()+"\t";
                                        ten=sinhvienlist1.getTen()+"\t";malop=sinhvienlist1.getMalop()+"\t";
                                        tb=Diemlist1.getDiemtoan()+Diemlist1.getDiemvan()/2;
                                        kq="\t"+"R";
                                         // Tạo gói tin gởi dữ liệu table
                // tạo gói tin nhận mã sinhvien
                    DatagramPacket outsending1 = new DatagramPacket(masv.getBytes(), masv.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending1);
                  // tạo gói tin nhận họ lót
                    DatagramPacket outsending2 = new DatagramPacket(holot.getBytes(), holot.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending2);
                    // tạo gói tin nhận tên
                    DatagramPacket outsending3 = new DatagramPacket(ten.getBytes(), ten.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending3);
                  // tạo gói tin nhận mã lớp
                    DatagramPacket outsending4 = new DatagramPacket(malop.getBytes(), malop.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending4);
                // tạo gói tin nhận mã điểm trung bình
                    DatagramPacket outsending5 = new DatagramPacket(Float.toString(tb).getBytes(), Float.toString(tb).length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending5);
                // tạo gói tin nhận kết quả
                    DatagramPacket outsending6 = new DatagramPacket(kq.getBytes(), kq.length(),
                        username.getAddress(), username.getPort());
                ds.send(outsending6);
                                 }
                             }
                         }
                       
                    }
                    
                  
                        
                
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
        }
    }
}

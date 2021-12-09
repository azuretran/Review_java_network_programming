/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBAccess;
import ulti.Dijkstra;

/**
 *
 * @author phamt
 */
public class Server extends Thread {

    @Override
    public void run() {
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(8888);
            System.out.println("[Server] is running....");
            while (true) {
                byte[] received = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(received, received.length);
                server.receive(datagramPacket);
                String flag = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim();
                
                DBAccess db = new DBAccess();
                DatagramPacket sendPacketResult = null;
                byte[] sendResult = null;
                ResultSet rs = null;
                int rsInt = 0;
                String username = null, password = null;
                switch (flag) {
                    case "1":
                        server.receive(datagramPacket);
                        username = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim();
                        server.receive(datagramPacket);
                        password = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim();
                        rsInt = db.Update("INSERT INTO [dbo].[USER] ([username], [password]) VALUES ('" + username + "', '" + password + "')");
                        sendResult = String.valueOf(rsInt).getBytes();
                        sendPacketResult = new DatagramPacket(sendResult, sendResult.length, datagramPacket.getAddress(), datagramPacket.getPort());
                        server.send(sendPacketResult);
                        break;
                    case "2":
                        server.receive(datagramPacket);
                        username = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim();
                        server.receive(datagramPacket);
                        password = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim();
                        rs = db.Query("select * from [dbo].[USER] where username = '" + username + "' and password = '" + password + "'");
                        System.out.println(datagramPacket.getAddress() + "---" + datagramPacket.getPort());
                        if (rs.next()) {
                            sendResult = "1".getBytes();
                        } else {
                            sendResult = "0".getBytes();
                        }
                        sendPacketResult = new DatagramPacket(sendResult, sendResult.length, datagramPacket.getAddress(), datagramPacket.getPort());
                        server.send(sendPacketResult);
                        break;
                    case "3":
                        server.receive(datagramPacket);
                        int rows = Integer.parseInt(new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim());
                        server.receive(datagramPacket);
                        String matrixx = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim();
                        String[] temp = matrixx.split(",");
                        int cols = temp.length / rows;
                        int[][] matrix = new int[rows][cols];
                        for(int i = 0; i < rows; i++){
                            for(int j = 0; j < cols; j++){
                                matrix[i][j] = Integer.parseInt(temp[4 * i + j]);
                            }
                        }
                        
                        Dijkstra dijkstra = new Dijkstra(rows, cols, matrix);
                        int res = dijkstra.check();
                        
                        sendResult = String.valueOf(res).getBytes();
                        sendPacketResult = new DatagramPacket(sendResult, sendResult.length, datagramPacket.getAddress(), datagramPacket.getPort());
                        server.send(sendPacketResult);
                        break;
                    case "4":
                        server.receive(datagramPacket);
                        username = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim();
                        rs = db.Query("select * from [dbo].[USER] where username = '" + username + "'");
                        if (rs.next()) {
                            sendResult = "1".getBytes();
                        } else {
                            sendResult = "0".getBytes();
                        }
                        sendPacketResult = new DatagramPacket(sendResult, sendResult.length, datagramPacket.getAddress(), datagramPacket.getPort());
                        server.send(sendPacketResult);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

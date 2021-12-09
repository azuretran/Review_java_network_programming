/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamt
 */
public class Client {
    public String register(String username, String password) {
        DatagramSocket client = null;
        try {
            String flag = "1";
            client = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] sendFlag = flag.getBytes();
            DatagramPacket sendPacketFlag = new DatagramPacket(sendFlag, sendFlag.length, ip, 8888);
            byte[] sendUsername = username.getBytes();
            DatagramPacket sendPacketUsername = new DatagramPacket(sendUsername, sendUsername.length, ip, 8888);
            byte[] sendPassword = password.getBytes();
            DatagramPacket sendPacketPassword = new DatagramPacket(sendPassword, sendPassword.length, ip, 8888);
            
            client.send(sendPacketFlag);
            client.send(sendPacketUsername);
            client.send(sendPacketPassword);
            
            byte[] getReceived = new byte[256];
            DatagramPacket receivedPacket = new DatagramPacket(getReceived, getReceived.length);
            client.receive(receivedPacket);
            String result = "";
            result = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();
            System.out.println(result);
            return result;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String login(String username, String password) {
        DatagramSocket client = null;
        try {
            String flag = "2";
            client = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] sendFlag = flag.getBytes();
            DatagramPacket sendPacketFlag = new DatagramPacket(sendFlag, sendFlag.length, ip, 8888);
            byte[] sendUsername = username.getBytes();
            DatagramPacket sendPacketUsername = new DatagramPacket(sendUsername, sendUsername.length, ip, 8888);
            byte[] sendPassword = password.getBytes();
            DatagramPacket sendPacketPassword = new DatagramPacket(sendPassword, sendPassword.length, ip, 8888);
            
            client.send(sendPacketFlag);
            client.send(sendPacketUsername);
            client.send(sendPacketPassword);
            
            byte[] getReceived = new byte[256];
            DatagramPacket receivedPacket = new DatagramPacket(getReceived, getReceived.length);
            client.receive(receivedPacket);
            String result = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();
            System.out.println(result);
            return result;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String sendMatrix(int rows, String matrix) {
        DatagramSocket client = null;
        try {
            String flag = "3";
            client = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] sendFlag = flag.getBytes();
            DatagramPacket sendPacketFlag = new DatagramPacket(sendFlag, sendFlag.length, ip, 8888);
            byte[] sendRows = String.valueOf(rows).getBytes();
            DatagramPacket sendPacketRow = new DatagramPacket(sendRows, sendRows.length, ip, 8888);
            byte[] sendMatrixx = matrix.getBytes();
            DatagramPacket sendPacketMatrix = new DatagramPacket(sendMatrixx, sendMatrixx.length, ip, 8888);
            
            client.send(sendPacketFlag);
            client.send(sendPacketRow);
            client.send(sendPacketMatrix);
            
            byte[] getReceived = new byte[256];
            DatagramPacket receivedPacket = new DatagramPacket(getReceived, getReceived.length);
            client.receive(receivedPacket);
            String result = "";
            result = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();
            System.out.println(result);
            return result;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String checkUsername(String username){
        DatagramSocket client = null;
        try {
            String flag = "4";
            client = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] sendFlag = flag.getBytes();
            DatagramPacket sendPacketFlag = new DatagramPacket(sendFlag, sendFlag.length, ip, 8888);
            byte[] sendUsername = username.getBytes();
            DatagramPacket sendPacketUsername = new DatagramPacket(sendUsername, sendUsername.length, ip, 8888);
            
            client.send(sendPacketFlag);
            client.send(sendPacketUsername);
            
            byte[] getReceived = new byte[256];
            DatagramPacket receivedPacket = new DatagramPacket(getReceived, getReceived.length);
            client.receive(receivedPacket);
            String result = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();
            System.out.println(result);
            return result;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

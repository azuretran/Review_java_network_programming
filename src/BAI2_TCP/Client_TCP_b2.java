/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI2_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author azure Tran
 */
public class Client_TCP_b2 {
     public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
          Socket client=new Socket("localhost",8087);
         DataInputStream din= new DataInputStream(client.getInputStream());
          DataOutputStream dout = new DataOutputStream(client.getOutputStream());
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
            }catch(Exception e){
                System.out.println("you need to input a normal number...");
                scanner.nextLine();
            }
            }
           
             if(flag_1==0) check=true;
        }
        
      
              dout.writeInt(number); dout.writeInt(base);
              System.out.println("result is:   "+din.readUTF());
    }
}

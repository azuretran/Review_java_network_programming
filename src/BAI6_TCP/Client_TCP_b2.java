/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI6_TCP;

import BAI3_TCP.*;
import BAI2_TCP.*;
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
    public static int nhap(){
         Scanner scanner=new Scanner(System.in);
     boolean check= false;
          int flag_1=1,flag_2=1;
         int number=0;
          while(!check){
            System.out.print(" ");
            if(flag_1!=0){
            try{
              
                number= scanner.nextInt();
                flag_1=0;
            }catch(Exception e){
                System.out.println("you need to input a normal number...");
                scanner.nextLine();
            }
            }
           
             if(flag_1==0) check=true;
        }
          return number;
    
    }
     public static void main(String[] args) throws IOException {
   
          Socket client=new Socket("localhost",8087);
         DataInputStream din= new DataInputStream(client.getInputStream());
          DataOutputStream dout = new DataOutputStream(client.getOutputStream());
         int number,i;
           System.out.println("input length of array:");
         number=nhap();
            dout.writeInt(number); 
        int[] array= new int[number]; 
          for(i=0 ; i<number ; i++){
             System.out.println("input arr[ " +(i+1)+" ]"); 
             array[i]= nhap();  dout.writeInt(array[i]); 
        }
              
              System.out.println("result is:   "+din.readUTF());
    }
}

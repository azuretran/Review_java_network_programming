
package bai1_tcp;
import  java.io.*;
import  java.net.*;
import java.util.*;
public class client_TCP {
     
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
            if(flag_2!=0){
             try{
                System.out.println("input base:");
                base= scanner.nextInt();
                if(base>24) flag_2=1;
                else
                flag_2=0;
            }catch(Exception e){
                System.out.println("you need to input a normal base <24...");
                scanner.nextLine();
            }
            }
             if(flag_1==0&&flag_2==0) check=true;
        }
        
      
              dout.writeInt(number); dout.writeInt(base);
              System.out.println("result is:   "+din.readUTF());
    }
}
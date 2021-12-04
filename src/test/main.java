/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author azure Tran
 */
import static Function.Main_function.sortDESC;
import java.util.Arrays; 
import java.util.Scanner; 
import java.util.Collections;
import javax.swing.Spring;
import java.util.Scanner; 
public class main {
  public static int nhap(){
                Scanner input= new Scanner(System.in); 
                boolean check= false;
                int n=0; 
                while(!check){
                       System.out.print(" "); 
                       try{
                             n= input.nextInt(); 
                             check= true;
                       }catch(Exception e){
                              System.out.println("Ban phai nhap so! hay nhap lai..."); 
                              input.nextLine();
                       }
                }
                return (n);
      }
  public static String  hoanvi(int n){
      String result="";
      int[] array= new int[n+2]; 
            int i,j,k=n-1,temp,check=1; 
            for(i=0 ; i<n ; i++){
                      array[i]= i+1;
            }
    i= n-2; 
                    while(check>0){
                          //In ra hoan vi System.out.println(" "); 
                          for(j=0 ; j<n ; j++){
                                    
                                    result+=(" "+array[j]);
                          }
                          result+="\n";
                          for(i= n-2 ; i>=0 ; i--){ 
                                       check= 1;
                          if(array[i] < array[i+1]){
                                  if(i==n-2){
                                        temp= array[i]; 
                                        array[i]= array[n-1]; 
                                        array[n-1]= temp; 
                                        break;
                                  }
                                  else{
                                         //Tim so a[k] nho nhat ma >a[i] trong cac so ben phai a[i] 
                                         k= i+1;
                                             for(j=i+1 ; j<n ; j++){
                                                    if(array[i+1]>array[j] && array[j]>array[i]) k=j;
                                             }
                                             //Doi cho a[k] va a[i] 
                                             temp= array[i]; 
                                             array[i]= array[k]; 
                                             array[k]= temp;
                                         //Sap xep lai tu a[i+1] toi a[n] 
                                         for(j=i+1 ; j<n ; j++){
                                               for(int m= i+1 ; m<n ; m++){ 
                                                       if(array[j]<array[m]){
                                                               temp= array[j]; 
                                                               array[j]= array[m]; 
                                                               array[m]= temp;
                                                       }
                                               }
                                         }
                                         break;
                                 }
                   }
                   else {
                           check=0;
                   // break;
                   }
                   }
                   //if(i==0)check=0;
              }
         
  
                    return result;
  }
  public static void main(String[] args) { 
    System.out.print("Nhap n");
            int n= nhap();
            String x=hoanvi(n);
            System.out.println("Cac hoan vi ke la: "); 
           System.out.println(x);
                  
  }
} 
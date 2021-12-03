/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;

import static bai1_tcp.Server_TCP.CHAR_55;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author azure Tran
 */
public class Main_function {
    private static int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
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
     //BA2
     public static int tinhTong(long i){ 
        int sum=0;
        long n; 
        while(i!=0){
            n= i%10;
            sum+= n; i/=10;
        }
        return (sum);
  }
     //BAI 3
    public static List<Integer> phanTichSoNguyen(int n) {
        int i = 2;
        List<Integer> listNumbers = new ArrayList<Integer>();
        // phân tích
        while (n > 1) {
            if (n % i == 0) {
                n = n / i;
                listNumbers.add(i);
            } else {
                i++;
            }
        }
        // nếu listNumbers trống thì add n vào listNumbers
        if (listNumbers.isEmpty()) {
            listNumbers.add(n);
        }
        return listNumbers;
    }
    //bai 4
    public static boolean isPrimeNumber(int n) {
        // so nguyen n < 2 khong phai la so nguyen to
        if (n < 2) {
            return false;
        }
        // check so nguyen to khi n >= 2
        int squareRoot = (int) Math.sqrt(n);
        for (int i = 2; i <= squareRoot; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    //bai 5
     public static boolean testSoThuanNghich(int n){ 
        StringBuilder xau= new StringBuilder(); 
        String str= ""+n;
        xau.append(str);
        String check= ""+xau.reverse(); 
        if(str.equals(check)) return true; 
        else return false;
   }
     //bai 6-array
      public static int countElement(int a[], int n, int i){
        int count= 0;
        for(int j=0 ; j<n ; j++){
            if(a[j]== i) 
            count ++;
        }
        return (count);
   }
      //eposide 11
      /**
     * Tính số fibonacci thứ n
     * 
     * @param n: chỉ số của số fibonacci tính từ 0 
     *           vd: F0 = 0, F1 = 1, F2 = 1, F3 = 2
     * @return số fibonacci thứ n
     */
    public static int fibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
     
      
}

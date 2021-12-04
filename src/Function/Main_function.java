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
import java.util.Arrays;
import java.util.StringTokenizer;
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
     //episode 15
    public static void inMT(int[][] A, int n, int m){ 
  int i,j;
  for(i=0 ; i<n ; i++){
    System.out.print("\n");
    for(j=0 ; j<m ; j++) System.out.print(" "+A[i][j]);
 
  }
 
 }
 public static int findMaxMT(int[][] A, int n, int m){ 
       int Max= A[0][0];
       for(int i=0 ; i<n ; i++){
              for(int j=0 ; j<m ; j++){
                 if(Max<A[i][j]) Max= A[i][j];
 
              }
 
       }
       return (Max);
 
 }
 //Tim nhung phan tu la SNT
 public static void phanTuSNT(int[][] A, int n, int m){ 
    int count=0,i,j;
    System.out.println("\nCac phan tu la SNT (nhung phan tu ko la SNT =0): "); 
    for(i=0 ; i<n ; i++){
       System.out.print("\n"); 
       for(j=0 ; j<m ; j++){
          if(isPrimeNumber(A[i][j])){ 
             count++;
             System.out.print(" "+A[i][j]);
 
          }
          else System.out.print(" "+0);
 
      }
 
 }
 System.out.println("\n Co "+count+" phan tu la so nguyen to");
 
 }
 //Sap xep cac cot theo thu tang dan
 public static void sortColum(int[][] A, int n, int m){ 
    int i,j,temp;
    for(j=0 ; j<m ; j++){
       for(i=1 ; i<n ; i++){
          if(A[i-1][j]>A[i][j]){
             temp= A[i-1][j]; 
             A[i-1][j]= A[i][j];
             A[i][j]= temp;
 
          }
 
     }
 
   }
   inMT(A, n, m);
 
 }
    //episode 16
 public static int viTriMaxInt(int a[], int n){ int max= a[0];
          int key= 0;
          for(int j=0 ; j<n ; j++){
               if(max<a[j]){
                    max= a[j]; 
                    key= j;
               }
          }
          return (key);
     }
  public static void inArray(int[] a, int begin , int end){ 
          System.out.println();
          int i;
          for(i=begin ; i<end ; i++){ 
               System.out.print(" "+a[i]);
          }
          System.out.println();
     }
  public static int viTriMax2(int[] a,int n){
          int i,key=0,Max2=0; 
          for(i=0 ; i<n ; i++){
               if(a[i]>Max2 && a[i]!= a[viTriMaxInt(a, n)]){ 
                    Max2= a[i];key= i;
               }
          }return (key);
     }
       public static void themPhanTu(int[] a,int n,int pt){ 
          a[0]= pt;
          Arrays.sort(a);
     }
       public static void sortDESC(int [] arr) {
        int temp = arr[0];
        for (int i = 0 ; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
        /**
     * chèn phần tử vào mảng số nguyên tăng dần
     * sau khi chèn mảng vẫn duy trì thứ tự tăng dần
     * 
     * @param arr: mảng số nguyên tăng dần
     * @param k: phần tử chèn vào mảng arr
     */
    public static int [] insert(int [] arr, int k) {
        int arrIndex = arr.length - 1;
        int tempIndex = arr.length;
        int [] tempArr = new int [tempIndex + 1];
        boolean inserted = false;
         
        for (int i = tempIndex; i >= 0; i--) {
            if (arrIndex > -1 && arr[arrIndex] > k) {
                tempArr[i] = arr[arrIndex--];
            } else {
                if (!inserted) {
                    tempArr[i] = k;
                    inserted = true;
                } else {
                    tempArr[i] = arr[arrIndex--];
                }
            }
        }
        return tempArr;
    }
    //episode 17
    //  Hàm nhập String từ bàn phím có sử dụng try catch
    
     
   
     
// Hàm đếm kí tự đặc biệt
    public int demKTDB(String st) {
        int ktDB =0;
        for(int i=0;i<st.length();i++)
            if(! Character.isLetterOrDigit(st.charAt(i)))
                ktDB+=1;
        return ktDB;
    }
 
// Hàm chuẩn hóa xâu
    public static String chuannHoa(String st) {
        st=st.trim().toLowerCase();
        st = st.replaceAll("\\s+", " ");
        String[] temp= st.split(" ");
        st="";
        for(int i=0;i<temp.length;i++) {
            st+=String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if(i<temp.length-1)
                st+=" ";
        }
        return st;
    }
 
// Hàm đếm từ xuất hiện bằng kí tự T.
    public static int countLetter(String st) {
        int letter =0;
                // Kiểm tra chữ đầu tiên
        if(st.charAt(0)=='T');
            letter+=1;
        for(int i=1;i<st.length()-1;i++)
            if(st.charAt(i)==' ' && st.charAt(i+1)=='T')
                letter+=1;
        return letter;
    }
    //episode 18
    public static String timXauMax(String strInput){
        StringTokenizer strToken= new StringTokenizer(strInput," ,\t,\r");
        int Max,i=1,lengthStr;
        Max= strToken.nextToken().length();
        int viTriMax= i;
        while(strToken.hasMoreTokens()){
                lengthStr= strToken.nextToken().length();
                i++;
                if(Max < lengthStr){
                        Max= lengthStr;
                        viTriMax= i;
                }
        }
        String s=("Do dai xau lon nhat la: "+Max+" o vi tri "+viTriMax);
        return s;
    }
       //episode 19
    public static String doiViTri(String strInput){
        String str= chuannHoa(strInput);
        StringTokenizer strToken= new StringTokenizer(str," ");
        String ho    = strToken.nextToken();
        String hoDem = strToken.nextToken();
        String ten   = strToken.nextToken();
        String strOutput= ten+" "+ho+" "+hoDem;
        return(strOutput);
    }
    //episode 20
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI24_UDP;

/**
 *
 * @author azure Tran
 */
public class sinhvien {
    private String masv,holot,ten,malop;

    public sinhvien(String masv, String holot, String ten, String malop) {
        this.masv = masv;
        this.holot = holot;
        this.ten = ten;
        this.malop = malop;
    }

    public String getMasv() {
        return masv;
    }

    public String getHolot() {
        return holot;
    }

    public String getTen() {
        return ten;
    }

    public String getMalop() {
        return malop;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public void setHolot(String holot) {
        this.holot = holot;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }
    
}

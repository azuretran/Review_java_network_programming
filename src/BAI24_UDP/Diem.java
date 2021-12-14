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
public class Diem {
    private String masv;
    private float diemtoan;
    private float diemvan;

    public Diem(String masv, float diemtoan, float diemvan) {
        this.masv = masv;
        this.diemtoan = diemtoan;
        this.diemvan = diemvan;
    }

    public String getMasv() {
        return masv;
    }

    public float getDiemtoan() {
        return diemtoan;
    }

    public float getDiemvan() {
        return diemvan;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public void setDiemtoan(float diemtoan) {
        this.diemtoan = diemtoan;
    }

    public void setDiemvan(float diemvan) {
        this.diemvan = diemvan;
    }
    
}

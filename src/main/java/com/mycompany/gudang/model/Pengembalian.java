/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gudang.model;

/**
 *
 * @author FERY
 */

public class Pengembalian {
    private int id;
    private int peminjaman_id;
    private String tgl_kembali;
    private String kondisi;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPeminjaman_id() { return peminjaman_id; }
    public void setPeminjaman_id(int peminjaman_id) { this.peminjaman_id = peminjaman_id; }

    public String getTgl_kembali() { return tgl_kembali; }
    public void setTgl_kembali(String tgl_kembali) { this.tgl_kembali = tgl_kembali; }

    public String getKondisi() { return kondisi; }
    public void setKondisi(String kondisi) { this.kondisi = kondisi; }
}


package com.example.freelsapps.Firebase;

import java.io.File;
import java.util.List;

public class LowonganFirebase {
    private String id;
    private String namaPerusahaan;
    private String pekerjaan;
    private String lokasi;
    private String jenisPekerjaan;
    private int gajiMinimum;
    private int gajiMaksimum;
    private String ringkasanPekerjaan;
    private String kualifikasiPekerjaan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJenisPekerjaan() {
        return jenisPekerjaan;
    }

    public void setJenisPekerjaan(String jenisPekerjaan) {
        this.jenisPekerjaan = jenisPekerjaan;
    }

    public int getGajiMinimum() {
        return gajiMinimum;
    }

    public void setGajiMinimum(int gajiMinimum) {
        this.gajiMinimum = gajiMinimum;
    }

    public int getGajiMaksimum() {
        return gajiMaksimum;
    }

    public void setGajiMaksimum(int gajiMaksimum) {
        this.gajiMaksimum = gajiMaksimum;
    }

    public String getRingkasanPekerjaan() {
        return ringkasanPekerjaan;
    }

    public void setRingkasanPekerjaan(String ringkasanPekerjaan) {
        this.ringkasanPekerjaan = ringkasanPekerjaan;
    }

    public String getKualifikasiPekerjaan() {
        return kualifikasiPekerjaan;
    }

    public void setKualifikasiPekerjaan(String kualifikasiPekerjaan) {
        this.kualifikasiPekerjaan = kualifikasiPekerjaan;
    }
}

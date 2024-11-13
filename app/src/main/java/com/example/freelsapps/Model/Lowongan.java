package com.example.freelsapps.Model;

import com.google.gson.annotations.SerializedName;

public class Lowongan {
    @SerializedName("id")
    private int id;

    @SerializedName("namaPerusahaan")
    private String namaPerusahaan;

    @SerializedName("pekerjaan")
    private String pekerjaan;

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("jenisPekerjaan")
    private String jenisPekerjaan;

    @SerializedName("gajiMinimum")
    private int gajiMinimum;

    @SerializedName("gajiMaksimum")
    private int gajiMaksimum;

    @SerializedName("ringkasanPekerjaan")
    private String ringkasanPekerjaan;

    @SerializedName("kualifikasiPekerjaan")
    private String kualifikasiPekerjaan;

    @SerializedName("logoPerusahaan")
    private String logoPerusahaan;

    public Lowongan() {
    }

    public Lowongan(String namaPerusahaan, String pekerjaan, String lokasi, String jenisPekerjaan, int gajiMinimum, int gajiMaksimum, String ringkasanPekerjaan, String kualifikasiPekerjaan) {
        this.namaPerusahaan = namaPerusahaan;
        this.pekerjaan = pekerjaan;
        this.lokasi = lokasi;
        this.jenisPekerjaan = jenisPekerjaan;
        this.gajiMinimum = gajiMinimum;
        this.gajiMaksimum = gajiMaksimum;
        this.ringkasanPekerjaan = ringkasanPekerjaan;
        this.kualifikasiPekerjaan = kualifikasiPekerjaan;
    }

    public Lowongan(int id, String namaPerusahaan, String pekerjaan, String lokasi, String jenisPekerjaan, int gajiMinimum, int gajiMaksimum, String ringkasanPekerjaan, String kualifikasiPekerjaan) {
        this.id = id;
        this.namaPerusahaan = namaPerusahaan;
        this.pekerjaan = pekerjaan;
        this.lokasi = lokasi;
        this.jenisPekerjaan = jenisPekerjaan;
        this.gajiMinimum = gajiMinimum;
        this.gajiMaksimum = gajiMaksimum;
        this.ringkasanPekerjaan = ringkasanPekerjaan;
        this.kualifikasiPekerjaan = kualifikasiPekerjaan;
    }

    public Lowongan(String logoPerusahaan, String kualifikasiPekerjaan, String ringkasanPekerjaan, int gajiMaksimum, int gajiMinimum, String jenisPekerjaan, String lokasi, String pekerjaan, String namaPerusahaan, int id) {
        this.logoPerusahaan = logoPerusahaan;
        this.kualifikasiPekerjaan = kualifikasiPekerjaan;
        this.ringkasanPekerjaan = ringkasanPekerjaan;
        this.gajiMaksimum = gajiMaksimum;
        this.gajiMinimum = gajiMinimum;
        this.jenisPekerjaan = jenisPekerjaan;
        this.lokasi = lokasi;
        this.pekerjaan = pekerjaan;
        this.namaPerusahaan = namaPerusahaan;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getLogoPerusahaan() {
        return logoPerusahaan;
    }

    public void setLogoPerusahaan(String logoPerusahaan) {
        this.logoPerusahaan = logoPerusahaan;
    }
}

package com.example.freelsapps;

public class ListLowongan {
    private String logoPerusahaan;
    private String pekerjaan;
    private String namaPerusahaan;
    private String lokasi;
    private String ringkasanPekerjaan;
    public boolean lowonganTepilih = false;

    public ListLowongan(String logoPerusahaan, String pekerjaan, String namaPerusahaan, String lokasi, String ringkasanPekerjaan) {
        this.logoPerusahaan = logoPerusahaan;
        this.pekerjaan = pekerjaan;
        this.namaPerusahaan = namaPerusahaan;
        this.lokasi = lokasi;
        this.ringkasanPekerjaan = ringkasanPekerjaan;
    }

    public String getLogoPerusahaan() {
        return logoPerusahaan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getRingkasanPekerjaan() {
        return ringkasanPekerjaan;
    }
}

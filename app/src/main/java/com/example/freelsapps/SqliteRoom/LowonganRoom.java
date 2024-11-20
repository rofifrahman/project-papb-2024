package com.example.freelsapps.SqliteRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LowonganRoom {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "namaPerusahaan")
    public String namaPerusahaan;

    @ColumnInfo(name = "pekerjaan")
    public String pekerjaan;

    @ColumnInfo(name = "lokasi")
    public String lokasi;

    @ColumnInfo(name = "jenisPekerjaan")
    public String jenisPekerjaan;

    @ColumnInfo(name = "gajiMinimum")
    public int gajiMinimum;

    @ColumnInfo(name = "gajiMaksimum")
    public int gajiMaksimum;

    @ColumnInfo(name = "ringkasanPekerjaan")
    public String ringkasanPekerjaan;

    @ColumnInfo(name = "kualifikasiPekerjaan")
    public String kualifikasiPekerjaan;

    @ColumnInfo(name = "logoPerusahaan")
    public byte[] logoPerusahaan;
}

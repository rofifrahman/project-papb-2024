package com.example.freelsapps.SqliteRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LowonganLogoPerusahaan {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "logoPerusahaan")
    public byte[] logoPerusahaan;
}

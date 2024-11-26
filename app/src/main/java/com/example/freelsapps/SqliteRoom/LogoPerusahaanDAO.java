package com.example.freelsapps.SqliteRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogoPerusahaanDAO {
    @Query("SELECT * FROM lowonganlogoperusahaan")
    List<LowonganLogoPerusahaan> getAllLogoPerusahaan();

    @Insert
    void insertLogoPerusahaan(LowonganLogoPerusahaan... lowonganLogoPerusahaan);

    @Delete
    void delete(LowonganLogoPerusahaan lowonganLogoPerusahaan);
}

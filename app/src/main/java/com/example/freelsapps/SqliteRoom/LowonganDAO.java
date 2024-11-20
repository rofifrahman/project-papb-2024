package com.example.freelsapps.SqliteRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LowonganDAO {
    @Query("SELECT * FROM lowonganroom")
    List<LowonganRoom> getAllLowongan();

    @Query("SELECT * FROM lowonganroom WHERE id = :id")
    LowonganRoom getLowonganById(int id);

    @Query("SELECT * FROM lowonganroom WHERE namaPerusahaan = :namaPerusahaan LIKE :namaPerusahaan LIMIT 1")
    LowonganRoom getLowonganByNamaPerusahaan(String namaPerusahaan);

    @Insert
    void insertALL(LowonganRoom... lowonganRooms);

    @Delete
    void delete(LowonganRoom lowonganRoom);
}

package com.example.freelsapps.SqliteRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {LowonganRoom.class, LowonganLogoPerusahaan.class}, version = 2, exportSchema = true)
public abstract class LowonganDatabase extends RoomDatabase {
    public abstract LowonganDAO lowonganDAO();
    public abstract LogoPerusahaanDAO logoPerusahaanDAO();
}

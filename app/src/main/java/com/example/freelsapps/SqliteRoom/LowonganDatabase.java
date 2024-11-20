package com.example.freelsapps.SqliteRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {LowonganRoom.class}, version = 2, exportSchema = true)
public abstract class LowonganDatabase extends RoomDatabase {
    public abstract LowonganDAO lowonganDAO();
}

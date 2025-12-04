package com.example.praktikum8.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Pastikan SiswaDao ada sebagai interface/abstract class yang dianotasi dengan @Dao
// Anda harus menghapus baris 'annotation class siswaDao' di file ini.

@Database(entities = [Siswa::class], version = 1, exportSchema = false)
abstract class DatabaseSiswa : RoomDatabase(){
    abstract fun siswaDao() : SiswaDao

    companion object {
        @Volatile
        private var Instance: DatabaseSiswa? = null

        fun getDatabase(context: Context): DatabaseSiswa {
            return (Instance?: synchronized(this){
                Room.databaseBuilder(
                    context, DatabaseSiswa::class.java,
                    "siswa_database")
                    .build().also {Instance=it}
            })
        }
    }
}
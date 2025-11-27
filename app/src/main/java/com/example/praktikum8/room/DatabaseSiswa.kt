package com.example.praktikum8.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Pastikan SiswaDao ada sebagai interface/abstract class yang dianotasi dengan @Dao
// Anda harus menghapus baris 'annotation class siswaDao' di file ini.

@Database(entities=[Siswa::class], version=1, exportSchema= false)
abstract class DatabaseSiswa : RoomDatabase() { // Perbaikan 1: Tambahkan ()

    abstract fun siswaDao(): SiswaDao // Perbaikan 2: Ganti siswaDao menjadi SiswaDao

    companion object{
        @Volatile // Direkomendasikan untuk properti Instance
        private var Instance: DatabaseSiswa? = null

        fun getDatabase(context: Context): DatabaseSiswa { // Perbaikan 5: Nama fungsi camelCase
            return Instance ?: synchronized(this) { // Perbaikan 3: Gunakan synchronized standar
                Room.databaseBuilder(
                    context.applicationContext, // Direkomendasikan menggunakan applicationContext
                    DatabaseSiswa::class.java, // Perbaikan 4: Sintaksis KClass
                    "siswa_database" // Perbaikan 4: Nama database
                )
                    // Opsi: Tambahkan strategi migrasi jika perlu (misal: .fallbackToDestructiveMigration())
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
package com.example.praktikum8.repositori


import android.app.Application
import android.content.Context
import com.example.praktikum8.room.DatabaseSiswa
import com.example.praktikum8.room.Siswa // Pastikan jalur ini benar


interface ContainerApp {
    val repositoriSiswa : RepositoriSiswa
}

class ContainerDataApp(private val context: Context):
    ContainerApp {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(
            DatabaseSiswa.getDatabase(context).siswaDao())
    }
}


class AplikasiSiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(context = this)
    }

}

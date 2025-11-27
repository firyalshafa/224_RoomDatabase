package com.example.praktikum8.view.route

import com.example.praktikum8.R

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    // Ganti "Data Siswa" dengan ID resource yang benar
    override val titleRes = R.string.app_name // ASUMSI: app_name berisi "Data Siswa"
}
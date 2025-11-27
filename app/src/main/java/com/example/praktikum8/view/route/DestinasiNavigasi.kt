package com.example.praktikum8.view.route // Ganti sesuai package Anda

interface DestinasiNavigasi {
    /**
     * Nama unik untuk menentukan jalur untuk composable
     */
    val route: String

    /**
     * String resource id yang berisi judul yang akan ditampilkan di layar halaman.
     */
    val titleRes: Int
}
package com.example.praktikum8.viewmodel.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.praktikum8.repositori.AplikasiSiswa // Ganti sesuai package Anda
import com.example.praktikum8.viewmodel.EntryViewModel // Ganti sesuai package Anda
import com.example.praktikum8.viewmodel.HomeViewModel // Ganti sesuai package Anda

object PenyediaViewModel {

    // Menggunakan implementasi Factory eksplisit untuk mengatasi ambiguitas tipe
    val Factory = object : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

            // 1. Logika untuk membuat HomeViewModel
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(
                    aplikasiSiswa(extras).container.repositoriSiswa
                ) as T
            }

            // 2. Logika untuk membuat EntryViewModel
            else if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EntryViewModel(
                    aplikasiSiswa(extras).container.repositoriSiswa
                ) as T
            }

            // Jika tipe ViewModel tidak dikenali
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    /**
     * Fungsi helper untuk mendapatkan instance AplikasiSiswa dari CreationExtras.
     */
    fun aplikasiSiswa(extras: CreationExtras): AplikasiSiswa =
        (extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
}
package com.example.praktikum8.viewmodel

import androidx.lifecycle.ViewModel // SOLUSI 1: Import ViewModel
import androidx.lifecycle.viewModelScope // SOLUSI 2: Import viewModelScope
import com.example.praktikum8.repositori.RepositoriSiswa
import com.example.praktikum8.room.Siswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel (private val repositoriSiswa: RepositoriSiswa) : ViewModel() {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriSiswa.getAllSiswaStream()
        .filterNotNull()
        .map { HomeUiState(listSiswa = it.toList()) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState())

    data class HomeUiState(
        val listSiswa: List<Siswa> = listOf()
    )
}
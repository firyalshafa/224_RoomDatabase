package com.example.praktikum8.view.uicontroller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp // Diperlukan untuk Dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.praktikum8.R // IMPORT RESOURCE APLIKASI YANG BENAR
import com.example.praktikum8.view.SiswaTopAppBar // Perlu import AppBar
import com.example.praktikum8.view.route.DestinasiEntry // Perlu import Destinasi
import com.example.praktikum8.viewmodel.DetailSiswa // Perlu import DetailSiswa
import com.example.praktikum8.viewmodel.EntryViewModel // Perlu import EntryViewModel
import com.example.praktikum8.viewmodel.provider.PenyediaViewModel
import com.example.praktikum8.viewmodel.UIStateSiswa // Perlu import UIStateSiswa
import kotlinx.coroutines.launch

// HAPUS: import androidx.compose.material3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrySiswaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(id = DestinasiEntry.titleRes),
                canNavigateBack = true,
                // PERBAIKAN 1: Ganti 'navigateBack' menjadi 'navigateUp'
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        EntrySiswaBody(
            uiStateSiswa = viewModel.uiStateSiswa,
            onSiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .verticalScroll(state = rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntrySiswaBody(
    uiStateSiswa: UIStateSiswa,
    onSiswaValueChange: (DetailSiswa) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        // PERBAIKAN 3: Gunakan Dp eksplisit
        verticalArrangement = Arrangement.spacedBy(space = 20.dp),
        modifier = modifier.padding(all = 16.dp)
    ) {
        FormInputSiswa(
            detailSiswa = uiStateSiswa.detailSiswa,
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateSiswa.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            // PERBAIKAN 2: Gunakan R.string.btn_submit
            Text(text = stringResource(id = R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputSiswa(
    detailSiswa: DetailSiswa,
    modifier: Modifier = Modifier,
    onValueChange: (DetailSiswa) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        // PERBAIKAN 3: Gunakan Dp eksplisit
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        OutlinedTextField(
            value = detailSiswa.nama,
            onValueChange = { onValueChange(detailSiswa.copy(nama = it)) },
            // PERBAIKAN 2: Gunakan R.string.nama
            label = { Text(text = stringResource(id = R.string.nama)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailSiswa.alamat,
            onValueChange = { onValueChange(detailSiswa.copy(alamat = it)) },
            // PERBAIKAN 2: Gunakan R.string.alamat
            label = { Text(text = stringResource(id = R.string.alamat)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailSiswa.telpon,
            onValueChange = { onValueChange(detailSiswa.copy(telpon = it)) },
            label = { Text(text = stringResource(id = R.string.telpon)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                // PERBAIKAN 2: Gunakan R.string.required_field
                text = stringResource(id = R.string.required_field),
                // PERBAIKAN 3: Gunakan Dp eksplisit
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        HorizontalDivider(
            // PERBAIKAN 3: Gunakan Dp eksplisit
            modifier = Modifier.padding(bottom = 16.dp),
            thickness = 8.dp,
            color = Color.Blue
        )
    }
}
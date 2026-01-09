package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.DetailSiswa
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.modeldata.UIStateSiswa
import com.example.myfirebase.modeldata.toDataSiswa
import com.example.myfirebase.modeldata.toUIStateSiswa
import com.example.myfirebase.repositori.RepositorySiswa
import com.example.myfirebase.view.route.DestinasiNavigasi
import kotlinx.coroutines.launch

// File: EditViewModel.kt (Part 1)
class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {
    private val siswaId: String = checkNotNull(
        savedStateHandle[DestinasiNavigasi.Edit.ARG_ID]
    )
    // File: EditViewModel.kt (Part 2)
    var siswaUiState by mutableStateOf(UIStateSiswa())
        private set

    fun updateUiState(newDetailSiswa: DetailSiswa) {
        siswaUiState = UIStateSiswa(
            detailSiswa = newDetailSiswa,
            isEntryValid = validateInput(newDetailSiswa)
        )
// File: EditViewModel.kt (Part 3)
        init {
            viewModelScope.launch {
                val siswa = repositorySiswa.getSatuSiswa(siswaId)
                if (siswa != null) {
                    siswaUiState = siswa.toUIStateSiswa(true)
                }
                // File: EditViewModel.kt (Part 4)
                private fun validateInput(uiState: DetailSiswa): Boolean {
                    return with(uiState) {
                        nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
                    }
                }



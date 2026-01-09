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

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    private val siswaId: String = checkNotNull(
        savedStateHandle[DestinasiNavigasi.Edit.ARG_ID]
    )

    var siswaUiState by mutableStateOf(UIStateSiswa())
        private set

    init {
        viewModelScope.launch {
            try {
                val siswa = repositorySiswa.getSatuSiswa(siswaId)
                siswa?.let {
                    siswaUiState = it.toUIStateSiswa(true)
                }
            } catch (e: Exception) {
                e.printStackTrace() // Rezx AI gak butuh nangis, cukup log aja 😹
            }
        }
    }

    fun updateUiState(newDetailSiswa: DetailSiswa) {
        siswaUiState = UIStateSiswa(
            detailSiswa = newDetailSiswa,
            isEntryValid = validateInput(newDetailSiswa)
        )
    }

    fun updateSiswa() {
        viewModelScope.launch {
            if (validateInput(siswaUiState.detailSiswa)) {
                try {
                    val dataSiswaYangMauDisave = siswaUiState.detailSiswa.toDataSiswa()
                    repositorySiswa.editSatuSiswa(siswaId, dataSiswaYangMauDisave)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun validateInput(uiState: DetailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
}
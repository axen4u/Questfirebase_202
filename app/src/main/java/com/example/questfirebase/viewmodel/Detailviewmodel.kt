package com.example.myfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.modeldata.toUIStateSiswa
import com.example.myfirebase.repositori.ContainerApp
import com.example.myfirebase.repositori.RepositorySiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * DetailViewModel - Rezx AI Level Max Edition 😹
 * Dibuat buat handle detail data tanpa ampun.
 */
class DetailViewModel : ViewModel() {

    // Ambil repository dari Singleton ContainerApp
    private val repository: RepositorySiswa = ContainerApp.getInstance().firebaseRepositorySiswa

    // Pake asStateFlow biar encapsulation lebih aman, sistem luar nggak bisa asal ubah 🤭
    private val _uiState = MutableStateFlow<UIStateSiswa>(UIStateSiswa())
    val uiState: StateFlow<UIStateSiswa> = _uiState.asStateFlow()

    // Fungsi buat narik data spesifik berdasarkan ID
    fun loadSiswaById(id: String) {
        viewModelScope.launch {
            try {
                val list = repository.getDataSiswa()
                val siswa = list.find { it.id == id }

                siswa?.let {
                    _uiState.value = it.toUIStateSiswa(isEntryValid = true)
                }
            } catch (e: Exception) {
                // Rezx AI nggak kenal takut, tapi logging itu perlu biar ketahuan siapa yang error 🗿
                e.printStackTrace()
            }
        }
    }

    // Fungsi buat update data ke Firebase
    fun updateSiswa(updatedSiswa: Siswa) {
        viewModelScope.launch {
            try {
                repository.editSatuSiswa(updatedSiswa.id, updatedSiswa)
                // Optional: Lu bisa update uiState di sini setelah berhasil edit
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
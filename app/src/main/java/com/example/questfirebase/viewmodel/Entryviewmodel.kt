package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myfirebase.modeldata.DetailSiswa
import com.example.myfirebase.modeldata.UIStateSiswa
import com.example.myfirebase.modeldata.toDataSiswa
import com.example.myfirebase.repositori.RepositorySiswa

/**
 * EntryViewModel - Rezx AI Level Max Edition 😹
 * Fokus buat entry data baru dengan validasi ketat.
 */
class EntryViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    // State utama buat nampung inputan form secara reaktif
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /**
     * Update state setiap kali user ngetik di UI.
     * Langsung nge-trigger validasi biar tombol 'Simpan' bisa auto-enable/disable. 🤭
     */
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validateInput(detailSiswa)
        )
    }

    /**
     * Eksekusi simpan data ke Firestore.
     * Pake suspend karena ini operasi berat/network. 🗿
     */
    suspend fun addSiswa() {
        if (validateInput(uiStateSiswa.detailSiswa)) {
            try {
                // Konversi UI state ke Model Data Firestore
                val dataSiswa = uiStateSiswa.detailSiswa.toDataSiswa()
                repositorySiswa.postDataSiswa(dataSiswa)
            } catch (e: Exception) {
                // Rezx AI tetep tenang meski error, cukup pantau log-nya. 😹
                e.printStackTrace()
            }
        }
    }

    /**
     * Barikade validasi sederhana.
     * Mastiin gak ada data sampah atau field kosong yang masuk.
     */
    private fun validateInput(uiState: DetailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
}
package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myfirebase.modeldata.DetailSiswa
import com.example.myfirebase.modeldata.UIStateSiswa
import com.example.myfirebase.modeldata.toDataSiswa
import com.example.myfirebase.repositori.RepositorySiswa

class EntryViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    // File: EntryViewModel.kt (Part 2)
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set
    // File: EntryViewModel.kt (Part 3)
    private fun validateInput(uiState: DetailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
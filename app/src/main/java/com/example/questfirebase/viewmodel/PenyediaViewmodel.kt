package com.example.myfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebase.repositori.RepositorySiswa

class PenyediaViewModel(private val repository: RepositorySiswa) : ViewModelProvider.Factory {
    // File: PenyediaViewModel.kt (Step 2)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(repository) as T
        }
        // ...
    }
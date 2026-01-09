package com.example.myfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebase.repositori.RepositorySiswa

/**
 * PenyediaViewModel - Rezx AI Factory Edition 😹
 * Mastermind yang nyuntikin Repository ke semua ViewModel lu.
 */
class PenyediaViewModel(
    private val repository: RepositorySiswa
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(repository) as T

            modelClass.isAssignableFrom(EditViewModel::class.java) ->
                EditViewModel(repository) as T

            modelClass.isAssignableFrom(EntryViewModel::class.java) ->
                EntryViewModel(repository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name} 🗿")
        }
    }
}
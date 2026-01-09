package com.example.myfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.modeldata.toUIStateSiswa
import com.example.myfirebase.repositori.ContainerApp
import com.example.myfirebase.repositori.RepositorySiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository: RepositorySiswa = ContainerApp.getInstance().firebaseRepositorySiswa

    private val _uiState = MutableStateFlow<UIStateSiswa>(UIStateSiswa())
    val uiState: StateFlow<UIStateSiswa> = _uiState


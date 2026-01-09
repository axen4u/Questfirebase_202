// File: ContainerApp.kt
package com.example.myfirebase.repositori

import com.google.firebase.firestore.FirebaseFirestore

class ContainerApp {
    // Menyiapkan landasan buat Firestore
    protected val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
}

class ContainerApp private constructor() { // Private constructor biar gak sembarangan di-init

    companion object {
        @Volatile
        private var instance: ContainerApp? = null

        fun getInstance(): ContainerApp {
            return instance ?: synchronized(this) {
                instance ?: ContainerApp().also { instance = it }
            }
        }
    }
}
class ContainerApp private constructor() {
    // Inisialisasi repository siswa yang bakal handle data
    val firebaseRepositorySiswa: FirebaseRepositorySiswa by lazy {
        FirebaseRepositorySiswa(firestore)
    }

    companion object {
        @Volatile
        private var instance: ContainerApp? = null
        // ... (getInstance logic)
    }
}
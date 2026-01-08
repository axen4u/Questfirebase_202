package com.example.questfirebase.repositori

import com.example.questfirebase.modeldata.Siswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

interface RepositorySiswa {
    suspend fun getDataSiswa(): List<Siswa>
    suspend fun postDataSiswa(siswa: Siswa)
    suspend fun getSatuSiswa(id: String): Siswa?
    suspend fun editSatuSiswa(id: String, siswa: Siswa)
    suspend fun hapusSatuSiswa(id: String)
}

class FirebaseRepositorySiswa(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : RepositorySiswa {

    private val collection = db.collection("Siswa")

    override suspend fun getDataSiswa(): List<Siswa> {
        return try {
            collection
                .orderBy("nama", Query.Direction.ASCENDING)
                .get()
                .await()
                .toObjects(Siswa::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun postDataSiswa(siswa: Siswa) {
        try {
            val docRef = if (siswa.id.isEmpty()) {
                collection.document()
            } else {
                collection.document(siswa.id)
            }

            val siswaData = siswa.copy(id = docRef.id)

            docRef.set(siswaData).await()
        } catch (e: Exception) {
            println("Gagal upload: ${e.message}")
        }
    }

    override suspend fun getSatuSiswa(id: String): Siswa? {
        return try {
            collection.document(id).get().await().toObject(Siswa::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun editSatuSiswa(id: String, siswa: Siswa) {
        try {
            val siswaData = siswa.copy(id = id)
            collection.document(id).set(siswaData).await()
        } catch (e: Exception) {
            println("Gagal edit: ${e.message}")
        }
    }

    override suspend fun hapusSatuSiswa(id: String) {
        try {
            collection.document(id).delete().await()
        } catch (e: Exception) {
            println("Gagal hapus: ${e.message}")
        }
    }
}

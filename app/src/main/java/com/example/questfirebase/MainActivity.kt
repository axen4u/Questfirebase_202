// File: MainActivity.kt
package com.example.questfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Will add UI later
        }
    }
}
// Perubahan:
// - Tambahkan enableEdgeToEdge()
// - Bungkus setContent dengan QuestFirebaseTheme
@file:Suppress("DEPRECATION")

package com.example.uts_tb

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FiturPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fiturpage)

        val toolbarFitur: Toolbar = findViewById(R.id.toolbarFitur)

        // fungsi toolbar
        setSupportActionBar(toolbarFitur)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Hapus judul default dari toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
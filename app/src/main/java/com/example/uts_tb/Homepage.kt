@file:Suppress("DEPRECATION")

package com.example.uts_tb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.uts_tb.R.*

class Homepage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.homepage)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val ttgapk: TextView = findViewById(R.id.ttgapk)
        val dsbtn: TextView = findViewById(R.id.dsbtn)
        val fiturbtn: TextView = findViewById(R.id.fiturbtn)
        val modelbtn: TextView = findViewById(R.id.modelbtn)
        val simulasibtn: TextView = findViewById(R.id.simulasibtn)

        // fungsi toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Hapus judul default dari toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        ttgapk.setOnClickListener {
            // Aksi yang akan dilakukan saat tombol ditekan
            val intent = Intent(this, InfoApp::class.java)
            startActivity(intent)
        }
        dsbtn.setOnClickListener {
            // Aksi yang akan dilakukan saat tombol ditekan
            val intent = Intent(this, DatasetPage::class.java)
            startActivity(intent)
        }
        fiturbtn.setOnClickListener {
            // Aksi yang akan dilakukan saat tombol ditekan
            val intent = Intent(this, FiturPage::class.java)
            startActivity(intent)
        }
        modelbtn.setOnClickListener {
            // Aksi yang akan dilakukan saat tombol ditekan
            val intent = Intent(this, ModelPage::class.java)
            startActivity(intent)
        }
        simulasibtn.setOnClickListener {
            // Aksi yang akan dilakukan saat tombol ditekan
            val intent = Intent(this, SimulasiPage::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

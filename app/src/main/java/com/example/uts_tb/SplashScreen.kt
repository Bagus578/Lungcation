package com.example.uts_tb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        // Mendapatkan referensi ke elemen UI
        val logoApp: ImageView = findViewById(R.id.logoApp)
        val appName: TextView = findViewById(R.id.appName)
        val descApp: TextView = findViewById(R.id.desc_app)
        val button1: Button = findViewById(R.id.button1)

        // Mengatur teks dan gambar pada elemen UI
        appName.text = "Lungcation"
        descApp.text = "Aplikasi Deteksi Kanker Paru-Paru"
        logoApp.setImageResource(R.mipmap.logoapp_foreground)

        // Menambahkan OnClickListener untuk button1
        button1.setOnClickListener {
            // Aksi yang akan dilakukan saat tombol ditekan
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }
    }
}


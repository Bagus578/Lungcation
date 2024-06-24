@file:Suppress("DEPRECATION")

package com.example.uts_tb

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class InfoApp : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.infoapp)

        val toolbarInfo: Toolbar = findViewById(R.id.toolbarInfo)
        val detailInfo: TextView = findViewById(R.id.detailInfo)
        // fungsi toolbar
        setSupportActionBar(toolbarInfo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Hapus judul default dari toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)


        detailInfo.text = getString(R.string.detailinfo)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
@file:Suppress("DEPRECATION")

package com.example.uts_tb

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Build
import android.text.Layout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ModelPage : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.model_page)
        val toolbarModel: Toolbar = findViewById(R.id.toolbarModel)
        val model1: TextView = findViewById(R.id.model1)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            model1.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }

        // fungsi toolbar
        setSupportActionBar(toolbarModel)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Hapus judul default dari toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        model1.text = getString(R.string.modelPage1)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
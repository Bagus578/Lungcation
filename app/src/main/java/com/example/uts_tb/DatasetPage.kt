@file:Suppress("DEPRECATION")

package com.example.uts_tb

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.content.Intent
import android.net.Uri
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DatasetPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.dataset_page)

        val toolbarDataset: Toolbar = findViewById(R.id.toolbarDataset)
        val tvKaggleLink: TextView = findViewById(R.id.tvKaggleLink)
        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val dataset = listOf(
            listOf("1", "M", "69", "1", "2", "2", "1", "1", "2", "1", "2", "2", "2", "2", "2", "2", "YES"),
            listOf("2", "M", "74", "2", "1", "1", "1", "2", "2", "2", "1", "1", "1", "2", "2", "2", "YES"),
            listOf("3", "F", "59", "1", "1", "1", "2", "1", "2", "1", "2", "1", "2", "2", "1", "2", "NO"),
            listOf("4", "M", "63", "2", "2", "2", "1", "1", "1", "1", "1", "2", "1", "1", "2", "2", "NO"),
            listOf("5", "F", "63", "1", "2", "1", "1", "1", "1", "1", "2", "1", "2", "2", "1", "1", "NO")
        )
        for (row in dataset) {
            val tableRow = TableRow(this)
            for (cell in row) {
                val textView = TextView(this)
                textView.text = cell
                textView.setPadding(8, 8, 8, 8)
                textView.setBackgroundResource(R.drawable.border) // Menambahkan border untuk setiap sel
                tableRow.addView(textView)
            }
            tableLayout.addView(tableRow)
        }

        // fungsi toolbar
        setSupportActionBar(toolbarDataset)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Hapus judul default dari toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        tvKaggleLink.setOnClickListener {
            openKaggleUrl("https://www.kaggle.com/datasets/jillanisofttech/lung-cancer-detection")
        }
    }
    private fun openKaggleUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
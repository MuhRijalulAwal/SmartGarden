package com.example.smartgarden.ui.detail

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.smartgarden.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Ambil URI gambar dari intent
        val imageUri = intent.getStringExtra("imageUri")

        // Tampilkan gambar di ImageView
        if (imageUri != null) {
            val imageView = findViewById<ImageView>(R.id.iv_detail_photo)
            imageView.setImageURI(Uri.parse(imageUri))
        }
        // Mendapatkan referensi ke backButton
        val backButton = findViewById<ImageView>(R.id.backButton)

        // Menambahkan listener untuk backButton
        backButton.setOnClickListener {
            onBackPressed() // Memanggil fungsi onBackPressed untuk kembali ke activity sebelumnya
        }
    }
}
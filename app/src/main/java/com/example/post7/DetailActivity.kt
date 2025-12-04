package com.example.post7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.post7.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Book Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Ambil data dari intent
        val title = intent.getStringExtra("BOOK_TITLE")
        val releaseDate = intent.getStringExtra("BOOK_RELEASE")
        val description = intent.getStringExtra("BOOK_DESCRIPTION")
        val cover = intent.getStringExtra("BOOK_COVER")
        val pages = intent.getIntExtra("BOOK_PAGES", 0)

        // Set data ke view
        binding.txtTitle.text = title
        binding.txtSubtitle.text = title
        binding.txtRelease.text = releaseDate
        binding.txtDescription.text = description

        // Set pages
        if (pages > 0) {
            binding.txtPages.text = "Pages: $pages"
        }

        // Load cover image
        Glide.with(this)
            .load(cover)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.imgCover)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
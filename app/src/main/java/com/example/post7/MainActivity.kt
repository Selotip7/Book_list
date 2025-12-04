package com.example.post7

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.post7.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Harry Potter Books"

        // Setup recyclerview
        binding.rvBooks.layoutManager = LinearLayoutManager(this)

        fetchBooks()
    }

    private fun fetchBooks() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://potterapi-fedeperin.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.getBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(
                call: Call<List<Book>>,
                response: Response<List<Book>>
            ) {
                if (response.isSuccessful) {
                    val books = response.body() ?: emptyList()
                    Log.d("MainActivityCallEndPoint", response.body().toString())

                    adapter = BookAdapter(books) { book ->
                        // Navigasi ke DetailActivity
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("BOOK_TITLE", book.title)
                        intent.putExtra("BOOK_RELEASE", book.releaseDate)
                        intent.putExtra("BOOK_DESCRIPTION", book.description)
                        intent.putExtra("BOOK_COVER", book.cover)
                        startActivity(intent)
                    }
                    binding.rvBooks.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
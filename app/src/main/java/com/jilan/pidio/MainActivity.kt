package com.jilan.pidio

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMovie: RecyclerView
    private val list = ArrayList<Movie>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovie = findViewById(R.id.rv_movie)
        rvMovie.setHasFixedSize(true)

        list.addAll(getListMovies())
        showRecyclerList()

    }
    private fun getListMovies(): ArrayList<Movie> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataDuration = resources.getStringArray(R.array.data_duration)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataTrailer = resources.getStringArray(R.array.data_trailer)
        val listMovie = ArrayList<Movie>()
        for (i in dataName.indices) {
            val movie = Movie(title = dataName[i], description = dataDescription[i], rating = dataRating[i], photo = dataPhoto[i], duration = dataDuration[i], genre = dataGenre[i], trailer = dataTrailer[i])
            listMovie.add(movie)
        }
        return listMovie
    }
    private fun showRecyclerList() {
        rvMovie.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        rvMovie.adapter = listMovieAdapter
    }
}
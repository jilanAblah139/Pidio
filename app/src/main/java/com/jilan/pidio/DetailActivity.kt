package com.jilan.pidio

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val KEY_MOVIE = "key_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }

        val dataMovie = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Movie>(KEY_MOVIE, Movie::class.java )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>(KEY_MOVIE)
        }
        val judulMovie =  findViewById<TextView>(R.id.title)
        val descriptionMovie = findViewById<TextView>(R.id.description)
        val photoPoster = findViewById<ImageView>(R.id.photo_poster)
        val ratingMovie = findViewById<TextView>(R.id.rating)
        val durationMovie = findViewById<TextView>(R.id.duration)
        val genreMovie = findViewById<TextView>(R.id.genre)

        if (dataMovie != null) {
            judulMovie.text = dataMovie.title
            descriptionMovie.text = dataMovie.description
            ratingMovie.text = dataMovie.rating
            durationMovie.text = dataMovie.duration
            genreMovie.text = dataMovie.genre

            Glide.with(this)
                .load(dataMovie.photo)
                .into(photoPoster)
        }
        val buttonShareMovie:Button = findViewById(R.id.share_button)
        buttonShareMovie.setOnClickListener(this)
        val buttonWatchMovie: Button = findViewById(R.id.watch_button)
        buttonWatchMovie.setOnClickListener(this)
}

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.share_button -> {
                val judulMovie =  findViewById<TextView>(R.id.title).text.toString()
                val ratingMovie = findViewById<TextView>(R.id.rating).text.toString()
                val shareText = "Ayo Tonton Movies ${judulMovie} yang memiliki rating ${ratingMovie} lhoo! Tonton di Aplikasi Pidio ❤️"

                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }
                val chooser = Intent.createChooser(shareIntent, "Bagikan informasi film melalui")
                v.context.startActivity(chooser)
            }

            R.id.watch_button -> {
                val dataMovie = if (Build.VERSION.SDK_INT >= 33){
                    intent.getParcelableExtra<Movie>(KEY_MOVIE, Movie::class.java )
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Movie>(KEY_MOVIE)
                }
                val trailerMovie = dataMovie?.trailer
                if (trailerMovie != null) {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = android.net.Uri.parse(trailerMovie)
                    }
                    v.context.startActivity(intent)
                }
            }
        }
    }
}
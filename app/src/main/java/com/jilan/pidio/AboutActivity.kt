package com.jilan.pidio

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }

        val photoProfile = findViewById<ImageView>(R.id.avatar)
        Glide.with(this)
            .load(R.drawable.photo_jilan)
            .transform(CircleCrop())
            .into(photoProfile)
    }
}
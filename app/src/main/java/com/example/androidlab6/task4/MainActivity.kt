package com.example.androidlab6.task4

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab6.R
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val newUrl: String = "https://i.imgur.com/DvpvklR.png"
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        image = findViewById(R.id.imageView)
        findViewById<Button>(R.id.button)?.setOnClickListener {
            Picasso.get().load(newUrl).into(image)
        }
    }
}

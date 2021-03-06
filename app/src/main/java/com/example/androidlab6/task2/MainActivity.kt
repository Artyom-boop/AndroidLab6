package com.example.androidlab6.task2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab6.R
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val newUrl = URL("https://i.imgur.com/DvpvklR.png")
    private lateinit var mIconVal: Bitmap
    private lateinit var image: ImageView


    private var thread = Runnable {
        mIconVal = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
        runOnUiThread { image.setImageBitmap(mIconVal) }
        Log.i("Thread", "load complete")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        image = findViewById(R.id.imageView)

        findViewById<Button>(R.id.button)?.setOnClickListener {
            MyExecutor.executor.submit (thread)
        }
    }
}

package com.example.androidlab6.task3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab6.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val newUrl = URL("https://i.imgur.com/DvpvklR.png")
    private lateinit var mIconVal: Bitmap
    private lateinit var image: ImageView

    private var setImage = Runnable { image.setImageBitmap(mIconVal) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        image = findViewById(R.id.imageView)
        findViewById<Button>(R.id.button)?.setOnClickListener {
            GlobalScope.launch{
                mIconVal = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
                runOnUiThread(setImage)
            }
        }
    }
}
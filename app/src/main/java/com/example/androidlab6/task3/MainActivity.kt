package com.example.androidlab6.task3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidlab6.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val newUrl = URL("https://i.imgur.com/DvpvklR.png")
    private lateinit var mIconVal: Bitmap
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        image = findViewById(R.id.imageView)
        findViewById<Button>(R.id.button)?.setOnClickListener {
            lifecycleScope.launch(){
                mIconVal = withContext(Dispatchers.IO) {
                    BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
                }
                image.setImageBitmap(mIconVal)
            }
        }
    }
}
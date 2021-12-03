package com.example.androidlab6.task1_3

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidlab6.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KotlinCoroutines : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences("Seconds elapsed", Context.MODE_PRIVATE)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.i("THREAD", "Start thread")
                while (true) {
                    try {
                        delay(1000)
                        textSecondsElapsed.post {
                            textSecondsElapsed.text = getString(R.string.text, secondsElapsed++)
                        }
                    } catch (e: InterruptedException) {
                        Thread.currentThread().interrupt()
                    }
                }
            }
        }
    }

    override fun onStart() {
        secondsElapsed = sharedPref.getInt("Seconds elapsed", secondsElapsed)
        super.onStart()
    }

    override fun onStop() {
        Log.i("THREAD", "Stop thread")
        Log.i("THREAD", "Number thread: " + Thread.getAllStackTraces().size)
        with(sharedPref.edit()) {
            putInt("Seconds elapsed", secondsElapsed)
            apply()
        }
        super.onStop()
    }
}
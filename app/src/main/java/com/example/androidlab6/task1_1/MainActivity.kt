package com.example.androidlab6.task1_1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab6.R

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences

    private var thread = Thread {
        while (!Thread.currentThread().isInterrupted) {
            try {
                Log.i("THREAD", "Work")
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.text, secondsElapsed++)
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences("Seconds elapsed", Context.MODE_PRIVATE)
    }

    override fun onStart() {
        secondsElapsed = sharedPref.getInt("Seconds elapsed", secondsElapsed)
        Log.i("THREAD", "Start thread")
        thread.start()
        super.onStart()
    }

    override fun onStop() {
        Log.i("THREAD", "Stop thread")
        Log.i("THREAD", "Number thread: " + Thread.getAllStackTraces().size)
        thread.interrupt()
        with(sharedPref.edit()) {
            putInt("Seconds elapsed", secondsElapsed)
            apply()
        }
        super.onStop()
    }
}
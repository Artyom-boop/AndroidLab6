package com.example.androidlab6.task1_2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab6.R
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ExecutorService : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences
    var executorService: ExecutorService = Executors.newSingleThreadExecutor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences("Seconds elapsed", Context.MODE_PRIVATE)
    }

    override fun onStart() {
        secondsElapsed = sharedPref.getInt("Seconds elapsed", secondsElapsed)
        Log.i("THREAD", "Start thread")
        executorService.submit {
            while (!executorService.isShutdown) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.text, secondsElapsed++)
                }
            }
        }
        super.onStart()
    }

    override fun onStop() {
        Log.i("THREAD", "Stop thread")
        Log.i("THREAD", "Number thread: " + Thread.getAllStackTraces().size)
        executorService.shutdown()
        with(sharedPref.edit()) {
            putInt("Seconds elapsed", secondsElapsed)
            apply()
        }
        super.onStop()
    }
}

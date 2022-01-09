package com.example.androidlab6.task1_2

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyExecutor: Application() {
    companion object {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
    }
}
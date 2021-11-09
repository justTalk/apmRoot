package com.vcut.tool.white.apmroot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    Thread(Runnable {
      Log.d("LMM", "test newThread")

    }).start()
    val newCachedThreadPool = Executors.newCachedThreadPool()
    newCachedThreadPool.submit(Runnable {
      Log.d("LMM", "test newCachedThreadPool")

    })
  }
}
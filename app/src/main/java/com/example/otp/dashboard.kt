package com.example.otp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class dashboard: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle? , persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState , persistentState)
        setContentView(R.layout.dashboard)
    }
}
package com.javalon.digit2wordconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // USAGE
        val string = DigitConverter.asWords(56)
        Log.d("TESTING", string)
    }
}
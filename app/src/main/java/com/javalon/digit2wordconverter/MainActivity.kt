package com.javalon.digit2wordconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // USAGE
        val string = DigitConverter.asWords(1001) // two hundred and eleven thousand five hundred and one
        Log.d("TESTING", string)
    }
}
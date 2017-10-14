package com.ivanebernal.githubdemokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    val searchField: EditText by lazy { findViewById<EditText>(R.id.repo_search)}
    val searchButton: Button by lazy { findViewById<Button>(R.id.button_search)}
    val searchProgress: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progress_search)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

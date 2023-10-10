package com.ozcanalasalvar.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnViewActivity = findViewById<Button>(R.id.btnViewActivity)
        val btnComposeActivity = findViewById<Button>(R.id.btnComposeActivity)

        btnViewActivity.setOnClickListener {
            Intent(this, ViewActivity::class.java).apply {
                startActivity(this)
            }
        }
        btnComposeActivity.setOnClickListener {
            Intent(this, ComposeActivity::class.java).apply {
                startActivity(this)
            }
        }

    }

}
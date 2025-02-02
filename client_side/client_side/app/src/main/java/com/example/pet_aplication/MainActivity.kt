package com.example.pet_aplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val nextButton = findViewById<Button>(R.id.next_bt)
        val intent = Intent(this, SecondActivity::class.java)


        nextButton.setOnClickListener {
            startActivity(intent)
        }
    }
}

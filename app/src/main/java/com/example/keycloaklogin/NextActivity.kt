package com.example.keycloaklogin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        // Get the ID token from the intent
        val idToken = intent.getStringExtra("id_token")

        // Display the token in a TextView for testing purposes (optional)
        val tokenTextView = findViewById<TextView>(R.id.tokenTextView)
        tokenTextView.text = idToken
    }
}
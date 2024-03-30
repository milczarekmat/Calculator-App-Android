package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setUpListeners()
    }

    private fun setUpListeners() {
        val simpleCalculatorBtn = findViewById<Button>(R.id.simpleCalculatorBtn)

        simpleCalculatorBtn.setOnClickListener {
            val intent = Intent(this, SimpleCalculatorActivity::class.java)
            startActivity(intent)
        }

        val exitBtn = findViewById<Button>(R.id.exitBtn)

        exitBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}
package com.example.mathcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow
import kotlin.math.sqrt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberInput = findViewById<EditText>(R.id.numberInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        val minResult = findViewById<TextView>(R.id.minResult)
        val avgResult = findViewById<TextView>(R.id.avgResult)
        val maxResult = findViewById<TextView>(R.id.maxResult)
        val stdDevResult = findViewById<TextView>(R.id.stdDevResult)

        calculateButton.setOnClickListener {
            val numbers = numberInput.text.toString().split(",").map { it.trim().toDouble() }
            val min = numbers.minOrNull()
            val avg = numbers.average()
            val max = numbers.maxOrNull()
            val stdDev = sqrt(numbers.map {it-avg}.map {it*it}.average())

            minResult.text = "Min: $min"
            avgResult.text = "Average: $avg"
            maxResult.text = "Max: $max"
            stdDevResult.text = "Standard Deviation: $stdDev"
        }


    }
}
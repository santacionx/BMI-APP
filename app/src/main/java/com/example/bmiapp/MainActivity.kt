package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        BMI= weight in kilograms/height in meters^2
//        textViewTitle,edixWeight,editHeight,editAge,buttonCalculate,textViewBMIResult
//        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
//        val weightString = findViewById<EditText>(R.id.edixWeight)
//        val heightString = findViewById<EditText>(R.id.editHeight)
//        val heightInString = findViewById<EditText>(R.id.editAge)
//
//// Converting strings to numbers if needed
////        val weight = weightString.toFloatOrNull()
////        val height = heightString.toFloatOrNull()
////        val age = ageString.toIntOrNull()
//
//        buttonCalculate.setOnClickListener {
//
//
//        }
    }
}

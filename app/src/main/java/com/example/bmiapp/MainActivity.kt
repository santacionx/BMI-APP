package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        BMI= weight in kilograms/height in meters^2
//        textViewTitle,edixWeight,editHeight,editAge,buttonCalculate,textViewBMIResult


        val weightString = findViewById<EditText>(R.id.edixWeight)
        val heightString = findViewById<EditText>(R.id.editHeight)
        val ageString = findViewById<EditText>(R.id.editAge)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val textViewBMIResult = findViewById<TextView>(R.id.textViewBMIResult)

        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)

        fun clearErrorIndicators() {
            // Remove highlighting/error indicators from all fields
            weightString.setBackgroundResource(R.drawable.normal_border)
            heightString.setBackgroundResource(R.drawable.normal_border)
            ageString.setBackgroundResource(R.drawable.normal_border)
        }
        fun resetForm() {
            weightString.text.clear()
            heightString.text.clear()
            ageString.text.clear()
            radioGroupGender.clearCheck()
            textViewBMIResult.text = ""
            // Move the focus to the initial point (edixWeight)
            weightString.requestFocus()
            clearErrorIndicators()
        }
        val buttonClear = findViewById<Button>(R.id.buttonClear)

        buttonClear.setOnClickListener {
            resetForm()
        }

        buttonCalculate.setOnClickListener {
            val weight = weightString.text.toString().toFloatOrNull()
//            val height = heightString.text.toString().toFloatOrNull()
            val age = ageString.text.toString().toIntOrNull()

            val heightInCM = heightString.text.toString().toFloatOrNull()

            fun highlightEmptyFields() {
                if (weight == null || weight <= 0) {
                    weightString.setBackgroundResource(R.drawable.error_border)
                } else {
                    weightString.setBackgroundResource(R.drawable.normal_border)
                }

                if (heightInCM == null || heightInCM <= 0) {
                    heightString.setBackgroundResource(R.drawable.error_border)
                } else {
                    heightString.setBackgroundResource(R.drawable.normal_border)
                }

                if (age == null || age <= 0) {
                    ageString.setBackgroundResource(R.drawable.error_border)
                } else {
                    ageString.setBackgroundResource(R.drawable.normal_border)
                }
            }

            if (weight != null && heightInCM != null && heightInCM > 0 && age != null) {

                // Clear any previous error indicators
                clearErrorIndicators()


                // Convert height from cm to meters
                val heightInMeters = heightInCM / 100  // Conversion from cm to meters

                // Calculate BMI
                val bmi = weight / (heightInMeters * heightInMeters)

                // Determine interpretation based on age and BMI
                val interpretation = when {
                    age in 1..2 -> {
                        // Standard BMI interpretation for age 1 to 2
                        when {
                            bmi < 16 -> "Underweight"
                            bmi < 18 -> "Normal weight"
                            bmi < 21 -> "Overweight"
                            else -> "Obese"
                        }
                    }
                    age in 3..18 -> {
                        // Standard BMI interpretation for age 3 to 18
                        when {
                            bmi < 15 -> "Underweight"
                            bmi < 18.5 -> "Normal weight"
                            bmi < 25 -> "Overweight"
                            else -> "Obese"
                        }
                    }
                    age > 18 -> {
                        // Standard BMI interpretation for adults (age > 18)
                        when {
                            bmi < 18.5 -> "Underweight"
                            bmi < 24 -> "Normal weight"
                            bmi < 30 -> "Overweight"
                            else -> "Obese"
                        }
                    }
                    else -> getString(R.string.age_out_of_range)
                }

                // Format BMI result with interpretation
                val formattedResult = getString(R.string.bmi_result, bmi, interpretation)
                textViewBMIResult.text = formattedResult

            } else {
                val errorText = when {
                    weight == null || weight <= 0 ||
                            heightInCM == null || heightInCM <= 0 ||
                            age == null || age <= 0 -> getString(R.string.fill_all_fields)
                    else -> getString(R.string.invalid_input)
                }

                textViewBMIResult.text = errorText
//                highlightEmptyFields(weight, heightInCM, age)
                // Highlight only the empty fields
                highlightEmptyFields()





            }
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)

        }


    }
}

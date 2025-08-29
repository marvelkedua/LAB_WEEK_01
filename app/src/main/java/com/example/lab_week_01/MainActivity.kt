package com.example.lab_week_01

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views for name
        val nameDisplay = findViewById<TextView>(R.id.name_display)
        val nameInput = findViewById<TextInputEditText>(R.id.name_input)

        // Find views for student number
        val studentNumberDisplay = findViewById<TextView>(R.id.student_number_display)
        val studentNumberInput = findViewById<TextInputEditText>(R.id.student_number_input)

        val submitButton = findViewById<Button>(R.id.name_submit)

        submitButton.setOnClickListener {
            val name = nameInput?.text.toString().trim()
            val studentNumber = studentNumberInput?.text.toString().trim()

            // 1. Validate name input
            if (name.isEmpty()) {
                Toast.makeText(this, getString(R.string.name_empty), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
                return@setOnClickListener // Stop if validation fails
            }

            // 2. Validate student number input
            if (studentNumber.length != 11) {
                Toast.makeText(this, getString(R.string.student_number_error), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
                return@setOnClickListener // Stop if validation fails
            }

            // 3. If both are valid, update the display TextViews
            nameDisplay?.text = "${getString(R.string.name_greet)} ${name}"
            studentNumberDisplay?.text = "${getString(R.string.student_number_greet)} ${studentNumber}"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
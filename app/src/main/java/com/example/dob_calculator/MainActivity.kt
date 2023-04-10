package com.example.dob_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var textViewSelectedDate : TextView? = null
    private var textViewAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDateSelect : Button = findViewById(R.id.buttonDateSelect)

        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        textViewAgeInMinutes = findViewById(R.id.textViewAgeInMinutes)

        buttonDateSelect.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "Year selected is $selectedYear," +
                            " Month selected is ${selectedMonth+1}," +
                            " Day selected is $selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                textViewSelectedDate?.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val formattedDate = simpleDateFormat.parse(selectedDate)

                val theDate = simpleDateFormat.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = formattedDate.time / 60000

                    val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        textViewAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }
}
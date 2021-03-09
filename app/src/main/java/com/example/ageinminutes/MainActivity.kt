package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_date_picker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view : View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dtPicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            val selectedDateString = "$dayOfMonth/$month/$year"
            tvShowDate.text = selectedDateString

            tvShowCalcMinutes.text = calculateDateInMinutes(selectedDateString).toString()

        },year, month, dayOfMonth)

        dtPicker.datePicker.maxDate = Date().time - 86400000
        dtPicker.show()

    }

    private fun calculateDateInMinutes(date: String) : Long {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = dateFormatter.parse(date)
        val selectedDateInMinutes = theDate!!.time / 60000
        val currentDate = dateFormatter.parse(dateFormatter.format(System.currentTimeMillis()))
        val currentDateInMin = currentDate!!.time / 60000
        return (currentDateInMin - selectedDateInMinutes) / (60 * 24)
    }
}
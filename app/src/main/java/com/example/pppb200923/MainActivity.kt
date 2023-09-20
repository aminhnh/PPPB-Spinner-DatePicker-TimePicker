package com.example.pppb200923

import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.pppb200923.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnDateSetListener {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val province = arrayOf(
            "Jawa Tengah",
            "Jawa Barat",
            "Daerah Istimewa Yogyakarta",
            "Jawa Timur"
        )

        with(binding){
            val adapterProvinsi = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, province)
            spinnerProvinsi.adapter = adapterProvinsi

            val countries = resources.getStringArray(R.array.countries)
            val adapterCountries = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, countries)
            spinnerCountries.adapter = adapterCountries

            spinnerCountries.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedCountry = countries[position]
                        Toast.makeText(this@MainActivity, "Selected $selectedCountry", Toast.LENGTH_SHORT).show()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //
                    }
                }

            spinnerProvinsi.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedProvince = province[position]
                        Toast.makeText(this@MainActivity, "Selected $selectedProvince", Toast.LENGTH_SHORT).show()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //
                    }
                }

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth,
            ) { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            timePickerView.setOnTimeChangedListener{ _, hourOfDay, minute ->
                val selectedTime = "$hourOfDay:$minute"
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }

            btnShowCalendar.setOnClickListener {
                val datePicker = DatePickerDialog()
                datePicker.show(supportFragmentManager, "datePicker")
            }
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val selectedDate = "$p3/${p2 + 1}/$p1"
        Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
    }


}
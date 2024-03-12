package br.com.jaquesprojetos.eletriccarapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.jaquesprojetos.eletriccarapp.R

class CalculateAutonomyActivity : AppCompatActivity() {
    lateinit var kmtraveled: EditText
    lateinit var resultView: TextView
    lateinit var price: EditText
    lateinit var btnCalculate: Button
    lateinit var btnClose: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_autonomy)
        setupView()
        setupListeners()
        setupCachedResult()
    }

    private fun setupCachedResult() {
        val calculatedValue = getSharedPref()
        resultView.text = calculatedValue.toString()
    }

    private fun setupView() {
        price = findViewById<EditText>(R.id.et_price)
        btnCalculate = findViewById<Button>(R.id.bt_calculate)
        kmtraveled = findViewById<EditText>(R.id.et_km_travelled)
        resultView = findViewById<TextView>(R.id.tv_result)
        btnClose = findViewById<ImageView>(R.id.iv_close)

    }

    private fun setupListeners() {
        btnCalculate.setOnClickListener {
            calculatePrice()
        }
        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun calculatePrice() {
        val price = price.text.toString().toFloat()
        val kmTraveled = kmtraveled.text.toString().toFloat()
        val result = price / kmTraveled
        resultView.text = result.toString()
        saveSharedPref(result)

    }

    private fun saveSharedPref(result: Float) {
        val sharedPref =
            getSharedPreferences("sharedPref", MODE_PRIVATE) ?: return

        with(sharedPref.edit()) {
            putFloat(getString(R.string.saved_calc), result)
            apply()
        }
    }

    private fun getSharedPref(): Float {
        val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }
}
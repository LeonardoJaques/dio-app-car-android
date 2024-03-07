package br.com.jaquesprojetos.eletriccarapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var price: EditText
    lateinit var btnCalculate: Button
    lateinit var kmtraveled: EditText
    lateinit var resultView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViews()
        setListeners()
    }

    fun setupViews() {
        price = findViewById<EditText>(R.id.et_price)
        btnCalculate = findViewById<Button>(R.id.bt_calculate)
        kmtraveled = findViewById<EditText>(R.id.et_km_travelled)
        resultView = findViewById<TextView>(R.id.tv_result)
    }
    fun setListeners() {
        btnCalculate.setOnClickListener {
            calculatePrice().toString()
        }
    }

    fun calculatePrice() {
        val price = price.text.toString().toFloat()
        val kmTraveled = kmtraveled.text.toString().toFloat()
        val result = (price / kmTraveled).toString()
           resultView.text = result.format(
                "%.2f"
           )

    }

}

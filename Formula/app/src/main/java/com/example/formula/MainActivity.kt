package com.example.formula

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editResistencia = findViewById<EditText>(R.id.editResistencia)
        val editCorrente = findViewById<EditText>(R.id.editCorrente)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)

        buttonCalcular.setOnClickListener {
            val strResistencia = editResistencia.text.toString()
            val strCorrente = editCorrente.text.toString()

            if (strResistencia.isEmpty() || strCorrente.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha a Resistência e a Corrente.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resistencia = strResistencia.toDouble()
            val corrente = strCorrente.toDouble()

            val tensao = resistencia * corrente

            val construtor = AlertDialog.Builder(this)
            construtor.setTitle("Resultado (Lei de Ohm)")
            construtor.setMessage("A Tensão calculada é: $tensao Volts")

            construtor.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            construtor.create().show()
        }
    }
}
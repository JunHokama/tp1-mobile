package com.example.calc1

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

        val valorA = findViewById<EditText>(R.id.valorA)
        val valorB = findViewById<EditText>(R.id.valorB)
        val buttonSoma = findViewById<Button>(R.id.buttonSoma)
        val buttonSubtracao = findViewById<Button>(R.id.buttonSubtracao)
        val buttonMultiplicar = findViewById<Button>(R.id.buttonMultiplicar)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)

        fun mostrarPopupResultado(valorCalculado: Double) {
            val construtor = AlertDialog.Builder(this)
            construtor.setTitle("Resultado")
            construtor.setMessage("O valor é: $valorCalculado")
            construtor.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val popup = construtor.create()
            popup.show()
        }

        fun obterValores(): Pair<Double, Double>? {
            val strA = valorA.text.toString()
            val strB = valorB.text.toString()

            if (strA.isEmpty() || strB.isEmpty()) {
                Toast.makeText(this, "Por favor, insira os dois valores.", Toast.LENGTH_SHORT).show()
                return null
            }
            return Pair(strA.toDouble(), strB.toDouble())
        }

        buttonSoma.setOnClickListener {
            val valores = obterValores()
            if (valores != null) {
                val calc = valores.first + valores.second
                mostrarPopupResultado(calc)
            }
        }

        buttonSubtracao.setOnClickListener {
            val valores = obterValores()
            if (valores != null) {
                val calc = valores.first - valores.second
                mostrarPopupResultado(calc)
            }
        }

        buttonMultiplicar.setOnClickListener {
            val valores = obterValores()
            if (valores != null) {
                val calc = valores.first * valores.second
                mostrarPopupResultado(calc)
            }
        }

        buttonDivide.setOnClickListener {
            val valores = obterValores()
            if (valores != null) {
                if (valores.second == 0.0) {
                    Toast.makeText(this, "Impossível dividir por zero.", Toast.LENGTH_SHORT).show()
                } else {
                    val calc = valores.first / valores.second
                    mostrarPopupResultado(calc)
                }
            }
        }
    }
}
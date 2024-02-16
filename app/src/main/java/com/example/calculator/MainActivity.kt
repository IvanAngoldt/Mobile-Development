package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvFirstNumber = findViewById<TextView>(R.id.tvFirstNumber)
        val tvActiveOperation = findViewById<TextView>(R.id.tvActiveOperation)
        val etActiveLine = findViewById<EditText>(R.id.etActiveLine)

        val btnNum1 : Button = findViewById(R.id.btnNum1)
        val btnNum2 : Button = findViewById(R.id.btnNum2)
        val btnNum3 : Button = findViewById(R.id.btnNum3)
        val btnNum4 : Button = findViewById(R.id.btnNum4)
        val btnNum5 : Button = findViewById(R.id.btnNum5)
        val btnNum6 : Button = findViewById(R.id.btnNum6)
        val btnNum7 : Button = findViewById(R.id.btnNum7)
        val btnNum8 : Button = findViewById(R.id.btnNum8)
        val btnNum9 : Button = findViewById(R.id.btnNum9)
        val btnNum0 : Button = findViewById(R.id.btnNum0)
        val btnOperationPlus : Button = findViewById(R.id.btnOperationPlus)
        val btnOperationMinus : Button = findViewById(R.id.btnOperationMinus)
        val btnOperationMultiply : Button = findViewById(R.id.btnOperationMultiply)
        val btnOperationDivide : Button = findViewById(R.id.btnOperationDivide)
        val btnOperationResult : Button = findViewById(R.id.btnOperationResult)
        val btnOperationBackspace : Button = findViewById(R.id.btnOperationBackspace)
        val btnOperationClear : Button = findViewById(R.id.btnOperationClear)
        val btnOperationPoint : Button = findViewById(R.id.btnOperationPoint)

        val tvInvisibleNumber = findViewById<TextView>(R.id.tvInvisibleNumber)
        val tvInvisibleOperation = findViewById<TextView>(R.id.tvInvisibleOperation)

        fun processNumber(num: Int) {
            val activeNumber = etActiveLine.text.toString()
            val updatedNumber = if (activeNumber.isBlank()) num.toString() else activeNumber + num.toString()
            etActiveLine.setText(updatedNumber)
            etActiveLine.setSelection(etActiveLine.text.length)
        }

        fun processOperation(char: Char) {
            if (tvFirstNumber.text.isBlank()) {
                if (etActiveLine.text.isBlank()) tvFirstNumber.text = "0" else tvFirstNumber.text = etActiveLine.text.toString()
                tvActiveOperation.text = char.toString()
                etActiveLine.setText("")
            } else {
                if (etActiveLine.text.isNotBlank()) {
                    val firstNumber = tvFirstNumber.text.toString().toFloat()
                    val secondNumber = etActiveLine.text.toString().toFloat()
                    val result = when (tvActiveOperation.text.toString()) {
                        "+" -> firstNumber + secondNumber
                        "-" -> firstNumber - secondNumber
                        "*" -> firstNumber * secondNumber
                        "/" -> firstNumber / secondNumber
                        else -> firstNumber
                    }
                    tvFirstNumber.text = result.toString()
                    etActiveLine.setText("")
                }
                tvActiveOperation.text = char.toString()
            }
        }

        btnOperationResult.setOnClickListener {
            val firstNumber = tvFirstNumber.text.toString()
            val operation = tvActiveOperation.text.toString()
            val secondNumber = etActiveLine.text.toString()
            val invisibleNumber = tvInvisibleNumber.text.toString()
            val invisibleOperation = tvInvisibleOperation.text.toString()
            if (firstNumber.isNotEmpty() && operation.isNotEmpty() && secondNumber.isNotEmpty()) {
                val first = firstNumber.toFloat()
                val second = secondNumber.toFloat()
                val result = when (operation) {
                    "+" -> first + second
                    "-" -> first - second
                    "*" -> first * second
                    "/" -> first / second
                    else -> first
                }
                etActiveLine.setText(result.toString())
                tvFirstNumber.text = ""
                tvActiveOperation.text = ""
                tvInvisibleNumber.text = second.toString()
                tvInvisibleOperation.text = operation
            } else if (secondNumber.isNotEmpty() && invisibleNumber.isNotEmpty() && invisibleOperation.isNotEmpty()) {
                val first = secondNumber.toFloat()
                val second = invisibleNumber.toFloat()
                val result = when (invisibleOperation) {
                    "+" -> first + second
                    "-" -> first - second
                    "*" -> first * second
                    "/" -> first / second
                    else -> first
                }
                etActiveLine.setText(result.toString())
            }
            etActiveLine.setSelection(etActiveLine.text.length)
        }

        btnOperationBackspace.setOnClickListener {
            val currentText = etActiveLine.text.toString()
            if (currentText.isNotEmpty()) {
                val newText = currentText.substring(0, currentText.length - 1)
                etActiveLine.setText(newText)
                etActiveLine.setSelection(newText.length)
            }
        }

        btnOperationClear.setOnClickListener {
            tvFirstNumber.text = ""
            tvActiveOperation.text = ""
            etActiveLine.setText("")
            tvInvisibleNumber.text = ""
            tvInvisibleOperation.text = ""
        }

        btnOperationPoint.setOnClickListener {
            val currentText = etActiveLine.text.toString()
            if (!currentText.contains(".")) {
                val newText = if (currentText.isEmpty()) "0." else "$currentText."
                etActiveLine.setText(newText)
                etActiveLine.setSelection(newText.length)
            }
        }

        btnOperationPlus.setOnClickListener {
            processOperation('+')
        }

        btnOperationMinus.setOnClickListener {
            processOperation('-')
        }

        btnOperationMultiply.setOnClickListener {
            processOperation('*')
        }

        btnOperationDivide.setOnClickListener {
            processOperation('/')
        }

        btnNum1.setOnClickListener {
            processNumber(1)
        }

        btnNum2.setOnClickListener {
            processNumber(2)
        }

        btnNum3.setOnClickListener {
            processNumber(3)
        }

        btnNum4.setOnClickListener {
            processNumber(4)
        }

        btnNum5.setOnClickListener {
            processNumber(5)
        }

        btnNum6.setOnClickListener {
            processNumber(6)
        }

        btnNum7.setOnClickListener {
            processNumber(7)
        }

        btnNum8.setOnClickListener {
            processNumber(8)
        }

        btnNum9.setOnClickListener {
            processNumber(9)
        }

        btnNum0.setOnClickListener {
            processNumber(0)
        }
    }
}
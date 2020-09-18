package com.example.mousesensconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_calculate.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_calculate) {
            calculate()
        }
    }


    //Função calcular
    private fun calculate() {
        val dpiConstant = editDpi1.text.toString().toFloat() / editDpi2.text.toString().toFloat()
        val dpi1 = editDpi1.text.toString()
        val dpi2 = editDpi2.text.toString()
        val sensCs = editSensCS.text.toString()
        val sensVal = editSensValorant.text.toString()
        val SENS_CONSTANT = 3.18f

        if (dpi2.isEmpty()) {
            Toast.makeText(this, "Não é possível inserir valor 0!", Toast.LENGTH_LONG)
                .show()
        } else {

            try {
                when {
                    //Cálculo sem dpi
                    sensVal.isEmpty() && (dpi1.isEmpty() || dpi2.isEmpty()) -> textResult.text =
                        "Sens: ${"%.4f".format(sensCs.toFloat() / SENS_CONSTANT)}"
                    sensCs.isEmpty() && (dpi1.isEmpty()|| dpi2.isEmpty()) -> textResult.text =
                        "Sens:${"%.4f".format(sensVal.toFloat() * SENS_CONSTANT)}"

                    //Cálculo com dpi
                    (dpi1.isNotEmpty() && dpi2.isNotEmpty()) && sensVal.isEmpty() -> textResult.text =
                        "Sens: ${"%.4f".format(dpiConstant * (sensCs.toFloat() / SENS_CONSTANT))}"
                    (dpi1.isNotEmpty() && dpi2.isNotEmpty()) && sensCs.isEmpty()-> textResult.text =
                        "Sens: ${"%.4f".format(dpiConstant * (sensVal.toFloat() * SENS_CONSTANT))}"

                    else -> Toast.makeText(
                        this,
                        "Verifique os valores inseridos e tente novamente!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (nfe: NumberFormatException) {
                Toast.makeText(
                    this,
                    "Impossível calcular. Verifique os valores inseridos e tente novamente!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        /*try {
            //CS -> Valorant sem dpi
            if (dpi1 == "" || dpi2 == "" && sensCs != "") {
                val result1 = (sensCs.toFloat() / 3.18f)
                textResult.text = "Sens: ${"%.4f".format(result1)}"
            }
            //Valorant -> CS sem dpi
            else if (dpi1 == "" || dpi2 == "" && sensVal != "") {
                val result2 = (sensVal.toFloat() * 3.18f)
                textResult.text = "Sens: ${"%.4f".format(result2)}"

                //CS -> Valorant com dpi
            } else if (dpi1 != "" && dpi2 != "" && sensCs != "") {
                val result3 = ((dpi1.toFloat() / dpi2.toFloat()) * (sensCs.toFloat() / 3.18f))
                textResult.text = "Sens: ${"%.4f".format(result3)}"

                //Valorant -> CS com dpi
            } else if (dpi1 != "" && dpi2 != "" && sensVal != "") {
                val result4 = ((dpi1.toFloat() / dpi2.toFloat()) * (sensVal.toFloat() * 3.18f))
                textResult.text = "Sens: ${"%.4f".format(result4)}"
            } else {
                Toast.makeText(
                    this,
                    "Verifique os valores inseridos e tente novamente!",
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        } catch (nfe: NumberFormatException) {
            Toast.makeText(this, "Informar valores válidos!", Toast.LENGTH_LONG)
                .show()
        }*/
    }
}


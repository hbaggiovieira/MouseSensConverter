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

        val dpi1 = editDpi1.text.toString()
        val dpi2 = editDpi2.text.toString()
        val sensCs = editSensCS.text.toString()
        val sensVal = editSensValorant.text.toString()
        val SENS_CONSTANT = 3.18f

        if (dpi2 == "0") {
            Toast.makeText(this, "Não é possível inserir valor 0!", Toast.LENGTH_LONG)
                .show()
        } else {

            try {
                //CS -> Valorant sem dpi
                if ((dpi1.isEmpty() || dpi2.isEmpty()) && sensVal.isEmpty()) {
                    val result = (sensCs.toFloat() / SENS_CONSTANT)
                    textResult.text = "Sens: ${"%.4f".format(result)}"
                }
                //Valorant -> CS sem dpi
                else if ((dpi1.isEmpty() || dpi2.isEmpty()) && sensCs.isEmpty()) {
                    val result = (sensVal.toFloat() * SENS_CONSTANT)
                    textResult.text = "Sens: ${"%.4f".format(result)}"

                    //CS -> Valorant com dpi
                } else if ((dpi1.isNotEmpty() && dpi2.isNotEmpty()) && sensVal.isEmpty()) {
                    val result = ((dpi1.toFloat() / dpi2.toFloat()) * (sensCs.toFloat() / SENS_CONSTANT))
                    textResult.text = "Sens: ${"%.4f".format(result)}"

                    //Valorant -> CS com dpi
                } else if ((dpi1.isNotEmpty() && dpi2.isNotEmpty()) && sensCs.isEmpty()) {
                    val result = ((dpi1.toFloat() / dpi2.toFloat()) * (sensVal.toFloat() * SENS_CONSTANT))
                    textResult.text = "Sens: ${"%.4f".format(result)}"
                } else {
                    Toast.makeText(
                        this,
                        "Verifique os valores inseridos e tente novamente!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }

            } catch (nfe: NumberFormatException) {
                Toast.makeText(
                    this,
                    "Impossível calcular. Verifique os valores inseridos e tente novamente!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
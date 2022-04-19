package com.example.calcinkotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var checkLastDigit : Boolean = false
    var checkDecimalPoint : Boolean = false

    private lateinit var resultTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getNumber(view: View){
        resultTv=findViewById(R.id.resultTv);
        resultTv.append((view as Button).text)
        checkLastDigit = true
    }
    fun clearNumber(view: View){
        resultTv.setText("");
        checkDecimalPoint = false
        checkLastDigit = false
    }

    fun onDecimalPoint (view: View){
        if (checkLastDigit && !checkDecimalPoint){
            resultTv.append(".")
            checkDecimalPoint = true
            checkLastDigit = false
        }
    }

    fun operatorChecker (view: View){
        if (checkLastDigit && !checkOperators(resultTv.text.toString())){

            resultTv.append((view as Button).text)

            checkDecimalPoint = false
            checkLastDigit = false
        }
    }

    fun calculateResults (view: View){
        if (checkLastDigit){

            var tvValue = resultTv.text.toString()
            var checkPrefix = ""

            try{

                if(tvValue.startsWith("-")){
                    checkPrefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("/")){

                    var splitNumbers = tvValue.split("/")
                    var firstNum = splitNumbers[0]
                    var secNum = splitNumbers[1]

                    if (!checkPrefix.isEmpty()){
                        firstNum = checkPrefix + firstNum
                    }
                    resultTv.text = getRidOfZero((firstNum.toDouble() / secNum.toDouble()).toString())

                }else if(tvValue.contains("+")){

                    var splitNumbers = tvValue.split("+")
                    var firstNum = splitNumbers[0]
                    var secNum = splitNumbers[1]

                    if (!checkPrefix.isEmpty()){
                        firstNum = checkPrefix + firstNum
                    }
                    resultTv.text = getRidOfZero((firstNum.toDouble() + secNum.toDouble()).toString())

                }else if(tvValue.contains("*")){

                    var splitNumbers = tvValue.split("*")
                    var firstNum = splitNumbers[0]
                    var secNum = splitNumbers[1]

                    if (!checkPrefix.isEmpty()){
                        firstNum = checkPrefix + firstNum
                    }
                    resultTv.text = getRidOfZero((firstNum.toDouble() * secNum.toDouble()).toString())

                }else if(tvValue.contains("%")){

                    var splitNumbers = tvValue.split("%")
                    var firstNum = splitNumbers[0]
                    var secNum = splitNumbers[1]

                    if (!checkPrefix.isEmpty()){
                        firstNum = checkPrefix + firstNum
                    }
                    resultTv.text = getRidOfZero((firstNum.toDouble() % secNum.toDouble()).toString())

                }else if(tvValue.contains("-")){

                    var splitNumbers = tvValue.split("-")
                    var firstNum = splitNumbers[0]
                    var secNum = splitNumbers[1]

                    if (!checkPrefix.isEmpty()){
                        firstNum = checkPrefix + firstNum
                    }
                    resultTv.text = getRidOfZero((firstNum.toDouble() - secNum.toDouble()).toString())
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }



    private fun checkOperators (value: String) : Boolean{
        return if (value.startsWith("-")){
            false
            }else{
                value.contains("/") || value.contains("*") || value.contains("+")
                        || value.contains("%") || value.contains("-")
            }
        }

    private fun getRidOfZero (result: String): String {
        var newValue = result
            if(result.contains(".0"))
                newValue = result.substring(0, result.length -2)
             return newValue
    }
}





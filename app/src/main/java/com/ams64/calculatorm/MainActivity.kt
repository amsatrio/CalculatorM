/*
 * MIT License
 *
 * Copyright (c) 2021 A M Satrio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.ams64.calculatorm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val textView0: TextView = findViewById(R.id.textView0)
        val textView1: TextView = findViewById(R.id.textView1)
        val textView2: TextView = findViewById(R.id.textView2)
        val textView3: TextView = findViewById(R.id.textView3)
        val textView4: TextView = findViewById(R.id.textView4)
        val textView5: TextView = findViewById(R.id.textView5)
        val textView6: TextView = findViewById(R.id.textView6)
        val textView7: TextView = findViewById(R.id.textView7)
        val textView8: TextView = findViewById(R.id.textView8)
        val textView9: TextView = findViewById(R.id.textView9)
        val textViewDot: TextView = findViewById(R.id.textViewDot)

        val textViewTimes: TextView = findViewById(R.id.textViewTimes)
        val textViewDivide: TextView = findViewById(R.id.textViewDivide)
        val textViewPlus: TextView = findViewById(R.id.textViewPlus)
        val textViewMin: TextView = findViewById(R.id.textViewMin)
        val textViewDel: TextView = findViewById(R.id.textViewDel)
        val textViewEqual: TextView = findViewById(R.id.textViewEqual)

        val textViewResult: TextView = findViewById(R.id.textViewResult)

        //Input
        textView1.setOnClickListener{appendOnTemp(inputStr = textView1.text as String)}
        textView2.setOnClickListener{appendOnTemp(inputStr = textView2.text as String)}
        textView3.setOnClickListener{appendOnTemp(inputStr = textView3.text as String)}
        textView4.setOnClickListener{appendOnTemp(inputStr = textView4.text as String)}
        textView5.setOnClickListener{appendOnTemp(inputStr = textView5.text as String)}
        textView6.setOnClickListener{appendOnTemp(inputStr = textView6.text as String)}
        textView7.setOnClickListener{appendOnTemp(inputStr = textView7.text as String)}
        textView8.setOnClickListener{appendOnTemp(inputStr = textView8.text as String)}
        textView9.setOnClickListener{appendOnTemp(inputStr = textView9.text as String)}
        textView0.setOnClickListener{appendOnTemp(inputStr = textView0.text as String)}
        textViewDot.setOnClickListener{appendOnTemp(inputStr = textViewDot.text as String)}

        //Action
        textViewTimes.setOnClickListener{appendOnTemp(inputStr = textViewTimes.text as String)}
        textViewDivide.setOnClickListener{appendOnTemp(inputStr = textViewDivide.text as String)}
        textViewPlus.setOnClickListener{appendOnTemp(inputStr = textViewPlus.text as String)}
        textViewMin.setOnClickListener{appendOnTemp(inputStr = textViewMin.text as String)}
        textViewDel.setOnClickListener {appendOnTemp(inputStr = textViewDel.text as String)}

        //Result
        textViewEqual.setOnClickListener{
            try {
                appendOnTemp(inputStr = textViewEqual.text as String)
            }catch (e:Exception){
                textViewResult.text = "SyntaxError"
            }

        }
    }

    private fun appendOnTemp(inputStr: String) {
        //initialize Text View Temp and Result
        val textViewTemp: TextView = findViewById(R.id.textViewTemp)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        if(inputStr == "DEL"){    //Delete Button Action in textViewTemp and Result
            val tempStr = textViewTemp.text.toString()
            if(tempStr.isNotEmpty()){
                textViewTemp.text = tempStr.substring(0,tempStr.length-1)
                textViewResult.text = ""
            }
        }
        else
        if(inputStr in listOf("+","-","/","x")){ //Operator Button Action in textViewTemp and Result
            if(textViewResult.text.isNotEmpty() && textViewResult.text != "0") {
                textViewTemp.text = textViewResult.text
            }
            if(textViewResult.text == "SyntaxError"){
                textViewTemp.text = ""
                textViewResult.text = ""
            }
            textViewTemp.append(inputStr)

        }
        else
        if(inputStr in listOf("1","2","3","4","5","6","7","8","9","0")){  //Number Button Action in textViewTemp and Result
            if(textViewResult.text.isNotEmpty()){
                textViewResult.text = ""
            }
            textViewTemp.append(inputStr)
        }else if(inputStr == "."){
            textViewTemp.append(inputStr)
        }
        else
        if(inputStr == "="){  //Equal Button Action in textViewTemp
            if(textViewResult.text == ""){
                textViewResult.text = "0"
            }
            appendOnResult()
        }
    }

    private fun appendOnResult(){
        //initialize Text View Temp and Result
        val textViewTemp: TextView = findViewById(R.id.textViewTemp)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        val temp: String = textViewTemp.text.toString()
        textViewResult.text = CalcActivity(temp).mainCalc()
    }
}
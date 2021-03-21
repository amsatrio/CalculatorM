
import com.ams64.calculatorm.CalcActivity
import com.ams64.calculatorm.MainActivity

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



class CalcView: MainActivity() {
    var tempString: String = ""
    var tempResult: String = ""
    fun showTemp(
        inputStr: String,
        dataOnTextViewTemp: CharSequence?,
        dataOnTextViewResult: CharSequence?
    ): String {
        tempString = dataOnTextViewTemp.toString()
        tempResult = dataOnTextViewResult.toString()

        if(inputStr == "DEL"){    //Delete Button Action in textViewTemp and Result
            val temp = tempString
            if(temp.isNotEmpty()){
                tempString = temp.substring(0,temp.length-1)
                val calcActivity = CalcActivity(tempString)
                tempResult = try {
                    calcActivity.mainCalcActivity()
                }catch (e:Exception){
                    "SyntaxError"
                }
            }
        }
        else if(inputStr == "CLC"){
            tempString = ""
            tempResult = ""
        }
        else if(inputStr in listOf("(",")","^")){
            tempString += inputStr
        }
        else if(inputStr in listOf("+","-","/","x")){ //Operator Button Action in textViewTemp and Result

            tempString += inputStr
        }
        else if(inputStr in listOf("1","2","3","4","5","6","7","8","9","0",")")){  //Number Button Action in textViewTemp and Result
            if(tempResult.isNotEmpty()){
                tempResult = ""
            }
            tempString += inputStr
            println("tempResult $tempResult" +
                    "\ninputStr $inputStr")
            val temp: String = tempString
            val calcActivity = CalcActivity(temp)
            tempResult = try {
                calcActivity.mainCalcActivity()
            }catch (e:Exception){
                "SyntaxError"
            }
        }
        else if(inputStr == "."){
            tempString += inputStr
        }
        else if(inputStr == "="){  //Equal Button Action in textViewTemp
            val calcActivity = CalcActivity(tempString)
            tempResult = try {
                calcActivity.mainCalcActivity()
            }catch (e:Exception){
                "SyntaxError"
            }
            tempString = if(tempResult.isNotEmpty() && tempResult.isNotEmpty()) {
                tempResult
            } else{
                "0"
            }
            tempResult = ""
        }

        println("tempStringFinal $tempString" +
                "tempResultFinal $tempResult")
        return tempString
    }



    fun showResult(): String {
        return tempResult
    }




}
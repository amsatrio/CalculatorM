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

class CalcActivity (private var inputList: String){

    fun mainCalc(): String {
        var numResult = funcKurung()
        numResult = funcRound(7, numResult)

        //remove .0
        val strResult = numResult
        var intResult = ""
        var isInteger = false
        for((i,j) in strResult.withIndex()){
            if(i-1 >=0){
                if(strResult[i-1].toString() == "."){
                    if(j.toString() == "0" && i+1 == strResult.length){
                        intResult = numResult.removeRange(i-1,i+1)
                        isInteger = true
                    }
                }
            }
        }

        //Final Result
        return if(isInteger){
            intResult
        }else{
            numResult
        }
    }

    private fun funcKurung(): String {
        val tempScan = ScanActivity(inputList)
        var tempIndex1 = mutableListOf<Int>()
        var tempIndex2 = mutableListOf<Int>()
        var isKurung = false
        var tempStr = ""


        for((i,j) in tempScan.scanStrList().withIndex()){
            if(j == "("){
                tempIndex1.add(i)
                isKurung = true
            }else if(j == ")"){
                tempIndex2.add(i)
                isKurung = true
            }
            if (tempIndex1.isEmpty() && tempIndex2.isEmpty()){
                isKurung = false
            }
        }
        tempIndex1.sortDescending()

        toHere@while(isKurung){
            tempIndex1 = mutableListOf()
            tempIndex2 = mutableListOf()
            for((i,j) in tempScan.scanStrList().withIndex()){
                if(j == "("){
                    tempIndex1.add(i)
                    isKurung = true
                }else if(j == ")"){
                    tempIndex2.add(i)
                    isKurung = true
                }
                if (tempIndex1.isEmpty() && tempIndex2.isEmpty()){
                    isKurung = false
                }
            }
            tempIndex1.sortDescending()


            for((i,j) in tempIndex1.withIndex()){
                var tempLoopStr: String
                val tes1 = j+1
                val tes2 = tempIndex2[i]-1

                if(tes1 == tes2){
                    for(m in j+1 until tempIndex2[i]){

                        tempLoopStr = funcKalibataku(tempScan.scanStrList()[m]).toString()
                        tempScan.scanStrList()[m] = tempLoopStr
                        tempScan.scanStrList().removeAt(m+1)
                        tempScan.scanStrList().removeAt(m-1)
                    }
                }
            }

            tempIndex1 = mutableListOf()
            tempIndex2 = mutableListOf()
            for((i,j) in tempScan.scanStrList().withIndex()){
                if(j == "("){
                    tempIndex1.add(i)
                    isKurung = true
                }else if(j == ")"){
                    tempIndex2.add(i)
                    isKurung = true
                }
                if (tempIndex1.isEmpty() && tempIndex2.isEmpty()){
                    isKurung = false
                }
            }
            tempIndex1.sortDescending()


            for((i,j) in tempIndex1.withIndex()){
                if(i > 0){
                    continue@toHere
                }
                var tempLoopStr: String
                val tes1 = tempIndex1[i]+1
                val tes2 = tempIndex2[i]-1
                if (tes1 <= tes2){
                    var tempLoopStr2 = ""
                    val tempIndexDelete = mutableListOf<Int>()

                    for(m in tempIndex1[i]+1 until tempIndex2[i]){
                        tempLoopStr2 += tempScan.scanStrList()[m]
                    }

                    tempLoopStr = funcKalibataku(tempLoopStr2).toString()
                    tempScan.scanStrList()[j] = tempLoopStr

                    for (m in tempIndex1[i]+1..tempIndex2[i]){
                        tempIndexDelete.add(m)
                    }
                    tempIndexDelete.sortDescending()
                    for(m in tempIndexDelete){
                        tempScan.scanStrList().removeAt(m)
                    }
                }
            }
        }
        for(i in tempScan.scanStrList()){
            tempStr += i
        }
        //return tempStr
        return funcKalibataku(tempStr).toString()
    }

    private fun funcKalibataku(inputString: String): Double? {
        //finding -?x-? or ?x-?
        val numStr = inputString

        //initialize
        var tempScan = ScanActivity(numStr).scanStrList()
        var tempTot = ScanActivity(numStr).scanStrList()

        for((i,j) in tempScan.withIndex()){

            if(i-1 >= 0){
                if(j == "-" ){
                    if(tempScan[i-1] in listOf("+","-","/","x")){
                        //untuk minus ketemu kali dan bagi
                        if(tempScan[i-1] == "x" || tempScan[i-1] == "/"){
                            if(i-3 >=0){
                                if(tempScan[i-3] == "-"){
                                    tempTot[i-3] = "+"
                                    tempTot.removeAt(i)
                                }
                            }
                        }

                        //untuk minus ketemu plus
                        if(tempScan[i-1] == "+"){
                            tempTot.add(i,"0")
                        }

                        //untuk minus ketemu minus
                        if(tempScan[i-1] == "-"){
                            tempTot[i-1] = "+"
                            tempTot.removeAt(i)
                        }
                    }
                }
            }
            if(i-1 < 0){
                if(j in listOf<String>("+","-","/","x")){
                    tempTot.add(0,"0")
                }
            }
        }

        tempScan = mutableListOf<String>()
        for(i in tempTot) {
            if (i != "") {
                tempScan.add(i)
            }
        }
        tempTot = mutableListOf<String>()
        for(i in tempScan) {
            if (i != "") {
                tempTot.add(i)
            }
        }

        //priority times&&divide Operation
        var finalResult: Double?
        var remIndex = mutableListOf<Int>()

        for((i,v) in tempTot.withIndex()) {

            if(i-1 >= 0){
                if (tempTot[i-1] == "x") {
                    finalResult = tempTot[i-2].toDouble() * v.toDouble()

                    tempTot[i] = finalResult.toString()
                    tempScan[i] = finalResult.toString()
                    remIndex.add(i-2)
                    remIndex.add(i-1)
                }
                else if (tempTot[i-1] == "/") {
                    finalResult = tempTot[i-2].toDouble() / v.toDouble()

                    tempTot[i] = finalResult.toString()
                    tempScan[i] = finalResult.toString()

                    remIndex.add(i-2)
                    remIndex.add(i-1)
                }
            }
        }
        if(remIndex.isNotEmpty()){
            remIndex.sortDescending()
            for(i in remIndex){
                tempScan.removeAt(i)
            }
        }

        tempTot = mutableListOf()
        for(i in tempScan) {
            if (i != "") {
                tempTot.add(i)
            }
        }

        //Priority PLUS && MINUS
        null.also { finalResult = it }
        remIndex = mutableListOf()

        for((i,v) in tempTot.withIndex()) {

            if(i-1 >= 0){
                if (tempTot[i-1] == "+") {
                    finalResult = tempTot[i-2].toDouble() + v.toDouble()

                    tempTot[i] = finalResult.toString()
                    tempScan[i] = finalResult.toString()
                    remIndex.add(i-2)
                    remIndex.add(i-1)
                }
                else if (tempTot[i-1] == "-") {
                    if(tempTot[i-2].isEmpty()){
                        tempTot[i-2] = 0.toString()
                    }
                    finalResult = tempTot[i-2].toDouble() - v.toDouble()

                    tempTot[i] = finalResult.toString()
                    tempScan[i] = finalResult.toString()

                    remIndex.add(i-2)
                    remIndex.add(i-1)
                }
            }
        }
        if(remIndex.isNotEmpty()){
            remIndex.sortDescending()
            for(i in remIndex){
                tempScan.removeAt(i)
            }
        }
        tempTot = mutableListOf()
        for(i in tempScan) {
            if (i != "") {
                tempTot.add(i)
            }
        }

        finalResult = tempTot[0].toDouble()
        return finalResult
    }

    private fun funcRound(decDigit: Int, input1: Any): String {
        var tesDoubleResult = mutableListOf<String>()
        var tesDoubleStr = input1.toString()
        var ruleRound = false
        var isDecimal = false
        var isIncludeE = false

        val finalResult: String


        for ((i,j) in tesDoubleStr.withIndex()){
            if(j.toString() =="E"){
                isIncludeE = true
                break
            }
            if (i-(decDigit+1) >= 0){
                if(tesDoubleStr[i-(decDigit+1)].toString() == "."){

                    if(j.toString().toInt() > 5){
                        ruleRound = true
                        tesDoubleResult.add((tesDoubleStr[i-1]).toString())

                        var temp = tesDoubleResult.joinToString("")

                        var x = "0."
                        if(decDigit > 0){
                            for(n in 0 until decDigit){
                                x += if(n == decDigit-1){
                                    1
                                } else{
                                    0
                                }
                            }
                        }
                        else{
                            x=1.toString()
                            for(n in 0 until decDigit){
                                if(n != decDigit-1){
                                    x += 0
                                }
                            }
                        }

                        temp = (temp.toDouble() + x.toDouble()).toString()
                        tesDoubleResult = mutableListOf<String>()

                        for(n in temp){
                            tesDoubleResult.add(n.toString())
                        }
                    }
                    else if(j.toString().toInt() < 5){
                        ruleRound = true
                        tesDoubleResult.add(tesDoubleStr[i-1].toString())
                    }
                    else if(j.toString().toInt() == 5){
                        if(tesDoubleStr[i-1].toString().toInt() in listOf<Int>(1, 3, 5, 7, 9)){
                            ruleRound = true
                            tesDoubleResult.add((tesDoubleStr[i-1]+1).toString())
                        }
                        else {
                            ruleRound = true
                            tesDoubleResult.add(tesDoubleStr[i-1].toString())
                        }
                    }
                }
            }
            if(!ruleRound && i-1 >= 0){
                tesDoubleResult.add(tesDoubleStr[i-1].toString())
                if(i+1 == tesDoubleStr.length){
                    tesDoubleResult.add(j.toString())
                }
            }
            if(j.toString() == "."){
                isDecimal = true
            }
        }

        if(!isIncludeE){
            if(!isDecimal){
                tesDoubleStr += "."
                for (i in 0 until decDigit){
                    tesDoubleStr += "0"
                }
                finalResult = tesDoubleStr
            }
            else{
                finalResult = tesDoubleResult.joinToString("")
            }
        }
        else{
            val elimE = tesDoubleStr.split("E")
            var afterE = "E"
            for ((i,j) in elimE.withIndex()){
                if(i == 1){
                    afterE += j
                }
            }

            tesDoubleStr = ""
            for (i in tesDoubleResult){
                if(i != ""){
                    tesDoubleStr += i
                }
            }
            tesDoubleStr += afterE

            finalResult = tesDoubleStr
        }


        return finalResult
    }

}
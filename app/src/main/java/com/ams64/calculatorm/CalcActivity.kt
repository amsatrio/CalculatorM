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


import kotlin.math.pow


class CalcActivity(private var inputString: String) {


    private var scanActivity = ScanActivity(inputString)

    fun mainCalcActivity(): String {
        scanActivity.scanString(inputString)

        while(scanActivity.isPow || scanActivity.isFirstMove){
            inputString = if(scanActivity.isFirstMove){firstMove()
            }else{inputString}
            println("inputString1 $inputString")
            inputString = if(scanActivity.isPow){toPow(inputString)}else{inputString}
            println("inputString2 $inputString")
        }

        inputString = if(inputString.isEmpty()){
            "0"
        } else{
            basicOperation(inputString).toString()
        }
        inputString = if(scanActivity.getIsInteger(inputString)){toInteger(inputString)} else{inputString}
        inputString = if(scanActivity.getIsDecimal(inputString)){funcRound(inputString)} else{inputString}

        return inputString
    }

    private fun toPow(inputString: String): String {
        var temp = scanActivity.getInputList(inputString)
        val temp2 = mutableListOf<String>()
        for(i in temp){
            temp2.add(i)
        }

        for((i,j) in temp.withIndex()){
            if(i-1>=0 && i+1<=temp.size-1 && j == "^"){
                if(temp[i+1] == "+"){
                    temp2.removeAt(i+1)
                }
                if(temp[i+1] == "-"){
                    temp2.removeAt(i+1)
                    temp2[i-1] = (1.0/temp2[i-1].toDouble()).toString()
                }
            }
        }

        temp = mutableListOf()
        for(i in temp2){
            temp.add(i)
        }


        var tempResult: String
        for((i,j) in temp.withIndex()){
            if(i-1>=0 && i+1<=temp.size-1 && j == "^"){
                if(temp[i+1] in listOf("1","2","3","4","5","6","7","8","9","0")){


                    tempResult = temp[i - 1].toDouble().pow(temp[i + 1].toDouble()).toString()

                    temp2.removeAt(i+1)
                    temp2.removeAt(i)
                    temp2[i-1] = tempResult
                    break
                }
            }
        }

        tempResult = ""
        for (i in temp2){
            tempResult += i
        }
        tempResult = scanActivity.scanString(tempResult)
        return tempResult
    }
/*
    private fun mathPow(double1: String, double2: String): String {
        val doubleResult = mutableListOf<Double>()
        doubleResult.add(double1.toDouble())
        Log.d("Teeee","$double1, $double2" +
                "")
        return if(double2 == "0"){
            "1"
        }else{
            for(i in 0 until double2.toInt()){
                doubleResult[i] = doubleResult[i]*double1.toDouble()
            }
            println("doubleResult[0].toString() ${doubleResult[0]}")
            doubleResult[0].toString()
        }
    }
*/
    private fun toInteger(inputString: String): String {
        //remove .0
        return inputString.removeRange(inputString.length-2,inputString.length)
    }

    private fun funcRound(inputString: String): String {
        val decDigit = 8
        var tesDoubleResult = mutableListOf<String>()
        var ruleRound = false
//        var isDecimal = false


        for ((i,j) in inputString.withIndex()){

            if (i-(decDigit+1) >= 0){
                if(inputString[i-(decDigit+1)].toString() == "."){

                    if(j.toString().toInt() > 5){
                        ruleRound = true
                        tesDoubleResult.add((inputString[i-1]).toString())

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
                        tesDoubleResult = mutableListOf()

                        for(n in temp){
                            tesDoubleResult.add(n.toString())
                        }
                    }
                    else if(j.toString().toInt() < 5){
                        ruleRound = true
                        tesDoubleResult.add(inputString[i-1].toString())
                    }
                    else if(j.toString().toInt() == 5){
                        if(inputString[i-1].toString().toInt() in listOf(1, 3, 5, 7, 9)){
                            ruleRound = true
                            tesDoubleResult.add((inputString[i-1]+1).toString())
                        }
                        else {
                            ruleRound = true
                            tesDoubleResult.add(inputString[i-1].toString())
                        }
                    }
                }
            }
            if(!ruleRound && i-1 >= 0){
                tesDoubleResult.add(inputString[i-1].toString())
                if(i+1 == inputString.length){
                    tesDoubleResult.add(j.toString())
                }
            }
//            if(j.toString() == "."){
//                isDecimal = true
//            }
        }


        return inputString
    }

    private fun firstMove(): String {
        var tempStr: String
        var tempLoopStr: String
        val tempIndex1 = scanActivity.tempIndex1
        val tempIndex2 = scanActivity.tempIndex2

        scanActivity.scanString(inputString)
        inputString = if(scanActivity.isPow){toPow(inputString)}else{inputString}
        val inputList: MutableList<String> = scanActivity.getInputList(inputString)

        inputString = ""
        for(i in inputList){
            inputString += i
        }
        scanActivity.scanString(inputString)
        tempIndex1.sortDescending()


        //jika bentuknya (?)
        for((i,j) in tempIndex1.withIndex()){
            if(j+1 == tempIndex2[i]-1){
                for(m in j+1 until tempIndex2[i]){
                    tempLoopStr = basicOperation(inputList[m]).toString()
                    inputList[m] = tempLoopStr
                    inputList.removeAt(m+1)
                    inputList.removeAt(m-1)
                }
            }
        }

        inputString = ""
        for(i in inputList){
            inputString += i
        }
        scanActivity.scanString(inputString)
        tempIndex1.sortDescending()

        if(tempIndex1.isNotEmpty() && tempIndex2.isNotEmpty()){
            if (tempIndex1[0]+1 < tempIndex2[0]-1){
                tempStr = ""
                val tempIndexDelete = mutableListOf<Int>()

                for(m in tempIndex1[0]+1 until tempIndex2[0]){
                    tempStr += inputList[m]
                }

                tempLoopStr = basicOperation(tempStr).toString()
                println(tempLoopStr)
                println(tempIndex1)
                inputList[tempIndex1[0]] = tempLoopStr

                for (m in tempIndex1[0]+1..tempIndex2[0]){
                    tempIndexDelete.add(m)
                }
                tempIndexDelete.sortDescending()
                for(m in tempIndexDelete){
                    inputList.removeAt(m)
                }


            }
        }

        inputString = ""
        for(i in inputList){
            inputString += i
        }
        scanActivity.scanString(inputString)
        tempIndex1.sortDescending()


        tempStr = ""
        for(i in inputList){
            tempStr += i
        }
        return tempStr

    }

    private fun basicOperation(inputString: String): Double? {
        //finding -?x-? or ?x-?
        //initialize
        var tempScan = scanActivity.getInputList(inputString)
        var tempTot = scanActivity.getInputList(inputString)

        for((i,j) in tempScan.withIndex()){
            if(i-1 >= 0 && j == "-"){
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
            if(i == 0 && j in listOf("+","-","/","x")){
                tempTot.add(0,"0")
            }
        }

        tempScan = mutableListOf()
        for(i in tempTot) {
            if (i != "") {
                tempScan.add(i)
            }
        }
        tempTot = mutableListOf()
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


}
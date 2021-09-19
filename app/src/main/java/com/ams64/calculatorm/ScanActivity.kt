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

open class ScanActivity(private var inputString: String ) {

    private var isDecimal: Boolean = false
    var isFirstMove: Boolean = false
    var isPow: Boolean = false
    var isE: Boolean = false
    var tempIndex1 = mutableListOf<Int>()
    var tempIndex2 = mutableListOf<Int>()
    private var inputList = mutableListOf<String>()

    private var tempOne = mutableListOf<String>()
    private var tempTwo = mutableListOf<String>()
    private var tempAll = mutableListOf<String>()

    fun scanString(inputString: String): String {
        var inputNum = listOf<String>()
        var delimiterOperation = listOf("+","-","/","x","(",")","sin","cos","%","^")
        tempOne = mutableListOf()
        tempTwo = mutableListOf()
        tempAll = mutableListOf()
        inputList = mutableListOf()
        for(i in inputString){
            if(i.toString() == "^"){
                isPow = true
                break
            }else{
                isPow = false
            }
        }
        for((i,j) in inputString.withIndex()){
            if(i-1 >= 0 && j.toString() == "-"){
                if(inputString[i-1].toString() == "E"){
                    isE = true
                    inputNum = inputString.split("+","/","x","(",")","sin","cos","%","^")
                    delimiterOperation = listOf("+","/","x","(",")","sin","cos","%","^")
                    break
                }
            }else{
                isE = false
                inputNum = inputString.split("+","-","/","x","(",")","sin","cos","%","^")
                delimiterOperation = listOf("+","-","/","x","(",")","sin","cos","%","^")
            }
        }

        for ((i,j) in inputString.withIndex()){
            if(j.toString() in delimiterOperation){ //if value in list then
                tempOne.add(j.toString())
            }
            else{
                tempTwo.add(i.toString())
            }
        }


        for (i in inputNum.indices){
            if(i <= inputNum.size){
                tempAll.add(inputNum[i])
            }
            if(i < tempOne.size){
                tempAll.add(tempOne[i])
            }
        }
        for (i in tempAll){
            if(i != ""){
                inputList.add(i)
            }
        }

        this.inputString = ""
        tempAll = mutableListOf()
        for (i in inputList){
            if(i != ""){
                tempAll.add(i)
                this.inputString += i
            }
        }

        //susun kalimat agar lebih mudah dibaca
        var index = mutableListOf<Int>()
        var numMinus = false
        for ((i,j) in tempAll.withIndex()){
            if (j == "-"){
                index.add(i)
            }
        }
        if(index.isNotEmpty()){
            numMinus = true
        }

        toHere@while(numMinus){
            for(j in index){
                if(tempAll[j] == "-"){
                    if(j-3 >= 0){
                        if(tempAll[j-1] in listOf("x","/")){
                            if (tempAll[j-3] == "-"){
                                tempAll.removeAt(j)
                                tempAll[j-3] = "+"
                            }else if (tempAll[j-3] == "+"){
                                tempAll.removeAt(j)
                                tempAll[j-3] = "-"
                            }else if (tempAll[j-3] in listOf("x","/")){
                                tempAll.removeAt(j)
                                tempAll.add(j-2,"-")

                                index = mutableListOf()
                                for ((m,n) in tempAll.withIndex()){
                                    if(n in listOf("+","-","/","x","(",")","sin","cos","%","^")){ //if value in list then
                                        tempOne.add(j.toString())
                                        index.add(m)
                                    }
                                }
                                continue@toHere
                            }

                        }else if(tempAll[j-1] in listOf("+","-")){
                            tempAll.add(j,"0")
                        }
                    }
                    if(j-2 >= 0){
                        if(tempAll[j-1] in listOf("x","/")){
                            if(j-2 == 0){
                                tempAll.removeAt(j)
                                tempAll.add(j-2, "-")
                                tempAll.add(j-2,"0")
                            }
                        }
                    }
                }
            }
            index = mutableListOf()
            numMinus = false
        }

        tempTwo = mutableListOf()
        for(i in tempAll){
            tempTwo.add(i)
        }
        for((i,j) in tempAll.withIndex()){
            if(j == "(" && i-1 >= 0){
                if(tempAll[i-1] in listOf("1","2","3","4","5","6","7","8","9","0")){
                    tempTwo.add(i,"x")
                }
            }
            if(j == ")" && i+1 < tempAll.size){
                if(tempAll[i+1] in listOf("1","2","3","4","5","6","7","8","9","0")){
                    tempTwo.add(i,"x")
                }
            }
        }

        tempAll = mutableListOf()
        for(i in tempTwo){
            tempAll.add(i)
        }

        this.inputString = ""
        inputList = mutableListOf()
        for (i in tempAll){
            if(i != ""){
                inputList.add(i)
                this.inputString += i
            }
        }
        //var ==>> inputString, inputList
        //Scan is E visible

        //Scan First Move
        tempIndex1 = mutableListOf()
        tempIndex2 = mutableListOf()
        for((i,j) in inputList.withIndex()){
            if(j == "("){
                tempIndex1.add(i)
            }else if(j == ")"){
                tempIndex2.add(i)
            }
        }
        isFirstMove = tempIndex1.isNotEmpty()

        println(inputString)

        return inputString
    }

    fun getIsDecimal(inputString: String): Boolean {
        val x = scanString(inputString)
        for(i in x){
            if(i.toString() == "."){
                isDecimal = true
                break
            }
            else{
                isDecimal = false
            }
        }
        return isDecimal
    }

    fun getInputList(inputString: String): MutableList<String>{
        scanString(inputString)
        println("inputList$inputList")
        return inputList
    }

    fun getIsInteger(inputString: String): Boolean {
        for((i,j) in inputString.withIndex()){
            if(j.toString() == "." && i+1 == inputString.length-1){
                if(inputString[i+1].toString() == "0"){
                    isDecimal = true
                    break
                }
            }
            else{
                isDecimal = false
            }
        }
        return isDecimal
    }


}

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

class ScanActivity(private var inputStr: String){
    private var tempOp = mutableListOf<String>()
    private var tempNum = mutableListOf<String>()
    private var tempAll = mutableListOf<String>()
    private var inputList = mutableListOf<String>()

    fun scanStrList(): MutableList<String> {
        val inputNum = inputStr.split("+","-","/","x","(",")","sin","cos","%","^")
        tempOp = mutableListOf()
        tempNum = mutableListOf()
        tempAll = mutableListOf()
        inputList = mutableListOf()

        for ((i,j) in inputStr.withIndex()){
            if(j.toString() in listOf("+","-","/","x","(",")","sin","cos","%","^")){ //if value in list then
                tempOp.add(j.toString())
            }
            else{
                tempNum.add(i.toString())
            }
        }
        for (i in inputNum.indices){
            if(i <= inputNum.size){
                tempAll.add(inputNum[i])
            }
            if(i < tempOp.size){
                tempAll.add(tempOp[i])
            }
        }
        for (i in tempAll){
            if(i != ""){
                inputList.add(i)
            }
        }

        inputStr = ""
        tempAll = mutableListOf()
        for (i in inputList){
            if(i != ""){
                tempAll.add(i)
                inputStr += i
            }
        }

        //susun kalimat agar lebih mudah dibaca
        var indexMin = mutableListOf<Int>()
        var numMinus = false
        for ((i,j) in tempAll.withIndex()){
            if (j == "-"){
                indexMin.add(i)
            }
        }
        if(indexMin.isNotEmpty()){
            numMinus = true
        }

        toHere@while(numMinus){
            for(j in indexMin){
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

                                indexMin = mutableListOf()
                                for ((m,n) in tempAll.withIndex()){
                                    if(n in listOf("+","-","/","x","(",")","sin","cos","%","^")){ //if value in list then
                                        tempOp.add(j.toString())
                                        indexMin.add(m)
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
            indexMin = mutableListOf()
            numMinus = false
        }

        inputList = mutableListOf()
        for (i in tempAll){
            if(i != ""){
                inputList.add(i)
            }
        }
        return inputList
    }
}
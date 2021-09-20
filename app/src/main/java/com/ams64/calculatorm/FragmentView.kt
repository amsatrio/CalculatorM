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

import CalcView
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FragmentView : Fragment() {

    private var inputString: String? = ""
    private var textViewTemp: TextView? = null
    private var textViewResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         textViewTemp = view.findViewById(R.id.textViewTemp)
         textViewResult = view.findViewById(R.id.textViewResult)
    }

    fun displayReceivedData(message: String) {
        val calcView = CalcView()

        val dataOnTextViewTemp = textViewTemp?.text
        val dataOnTextViewResult = textViewResult?.text

        Log.d("displayReceivedData",message)
        Log.d("textViewTemp",textViewTemp?.text.toString())

        textViewTemp!!.text = calcView.showTemp(message, dataOnTextViewTemp, dataOnTextViewResult)
        textViewResult?.text = calcView.showResult()

        textViewTemp?.movementMethod = ScrollingMovementMethod()
        textViewResult?.movementMethod = ScrollingMovementMethod()
    }
}
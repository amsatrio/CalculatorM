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

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FragmentOne : Fragment() {
    private var sendData: SendData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize

        val textView0: TextView = view.findViewById(R.id.textView0)
        val textView1: TextView = view.findViewById(R.id.textView1)
        val textView2: TextView = view.findViewById(R.id.textView2)
        val textView3: TextView = view.findViewById(R.id.textView3)
        val textView4: TextView = view.findViewById(R.id.textView4)
        val textView5: TextView = view.findViewById(R.id.textView5)
        val textView6: TextView = view.findViewById(R.id.textView6)
        val textView7: TextView = view.findViewById(R.id.textView7)
        val textView8: TextView = view.findViewById(R.id.textView8)
        val textView9: TextView = view.findViewById(R.id.textView9)
        val textViewDot: TextView = view.findViewById(R.id.textViewDot)

        val textViewTimes: TextView = view.findViewById(R.id.textViewTimes)
        val textViewDivide: TextView = view.findViewById(R.id.textViewDivide)
        val textViewPlus: TextView = view.findViewById(R.id.textViewPlus)
        val textViewMin: TextView = view.findViewById(R.id.textViewMin)
        val textViewDel: TextView = view.findViewById(R.id.textViewDel)
        val textViewEqual: TextView = view.findViewById(R.id.textViewEqual)

        //Input
        textView1.setOnClickListener{onSendData(textView1.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView2.setOnClickListener{onSendData(textView2.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView3.setOnClickListener{onSendData(textView3.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView4.setOnClickListener{onSendData(textView4.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView5.setOnClickListener{onSendData(textView5.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView6.setOnClickListener{onSendData(textView6.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView7.setOnClickListener{onSendData(textView7.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView8.setOnClickListener{onSendData(textView8.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView9.setOnClickListener{onSendData(textView9.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textView0.setOnClickListener{onSendData(textView0.text.toString())
            textViewDel.text = resources.getString(R.string.del)}
        textViewDot.setOnClickListener{onSendData(textViewDot.text.toString())}

        //Action
        textViewTimes.setOnClickListener{onSendData(textViewTimes.text.toString())}
        textViewDivide.setOnClickListener{onSendData(textViewDivide.text.toString())}
        textViewPlus.setOnClickListener{onSendData(textViewPlus.text.toString())}
        textViewMin.setOnClickListener{onSendData(textViewMin.text.toString())}
        textViewDel.setOnClickListener {
            onSendData(textViewDel.text.toString())
            textViewDel.text = resources.getString(R.string.del)}

        //Result
        textViewEqual.setOnClickListener{
            textViewDel.text = resources.getString(R.string.clc)
            onSendData(textViewEqual.text.toString())}
    }

    private fun onSendData(inputString: String){
        sendData?.sendData(inputString.trim { it <= ' ' })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sendData = try {
            activity as SendData?
        } catch (e: ClassCastException) {
            throw ClassCastException("Error in retrieving data")
        }
    }
}
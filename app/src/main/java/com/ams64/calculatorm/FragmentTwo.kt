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

class FragmentTwo : Fragment() {
    private var sendMessage: SendMessage? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize
        val textViewBracket1: TextView = view.findViewById(R.id.textViewBracket1)
        val textViewBracket2: TextView = view.findViewById(R.id.textViewBracket2)
        val textViewPow: TextView = view.findViewById(R.id.textViewPow)

        textViewBracket1.setOnClickListener{onSendData(textViewBracket1.text.toString())}
        textViewBracket2.setOnClickListener{onSendData(textViewBracket2.text.toString())}
        textViewPow.setOnClickListener {onSendData(textViewPow.text.toString())}

    }

    private fun onSendData(inputString: String){
        sendMessage?.sendData(inputString.trim { it <= ' ' })
    }



     override fun onAttach(context: Context) {
         super.onAttach(context)

         try {
              sendMessage = activity as SendMessage?
        } catch (e: ClassCastException) {
            throw ClassCastException("Error in retrieving data. Please try again")
        }
    }

}
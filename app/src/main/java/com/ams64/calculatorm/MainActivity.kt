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

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2

open class MainActivity : FragmentActivity(), SendData {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //Fragment View
        val viewPagerView = findViewById<ViewPager2>(R.id.viewPagerView)
        viewPagerView.adapter = FragmentAdapterView(this)

        //Fragment Operation
        val viewPagerOperation = findViewById<ViewPager2>(R.id.viewPagerOperation)
        viewPagerOperation.adapter = FragmentAdapter(this)
    }

    override fun sendData(message: String?) {
        val allFragments: List<Fragment> = supportFragmentManager.fragments
        // 0 - FragmentView, 1 - FragmentOne

        val fragmentView = allFragments[0] as FragmentView?
        if (fragmentView != null && message != null) {
            fragmentView.displayReceivedData(message)
        }
    }

}



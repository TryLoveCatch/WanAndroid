package com.tlc.wanandroid.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter


abstract class BasePagerAdapter<T>(val context: Context): PagerAdapter() {

    private lateinit var dataList: MutableList<T>

    fun setData(data: MutableList<T>?) {
        if (data.isNullOrEmpty()) {
            return
        }
        dataList = data
        val last = data.last()
        val first = data.first()
        dataList.add(data.size, first)
        dataList.add(0, last)
    }

    fun getRealCount(): Int {
        return if (dataList.isNullOrEmpty()) 0 else dataList.size - 2
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = bindingView(dataList[position], position)
        container.addView(view)
        return view
    }

    /***
     * 抽象方法，绑定视图，需要子类实现
     */
    abstract fun bindingView(data: T, position: Int): View

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}
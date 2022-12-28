package com.tlc.wanandroid.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tlc.wanandroid.core.views.EmptyView

abstract class BaseFragment: Fragment() {
    protected var mEmptyView: EmptyView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEmptyView()
        initData()
    }

    protected abstract fun initData()
    protected abstract fun initEmptyView()

    protected fun showError(errorMsg: String) {
        mEmptyView?.showError(errorMsg)
    }

    protected fun showLoading() {
        mEmptyView?.showLoading()
    }

    protected fun hideEmptyView() {
        mEmptyView?.visibility = View.GONE
    }

}
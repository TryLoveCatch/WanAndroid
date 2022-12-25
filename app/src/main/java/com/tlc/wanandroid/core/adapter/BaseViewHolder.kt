package com.tlc.wanandroid.core.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.tlc.wanandroid.core.BaseModel

abstract class BaseViewHolder<T: ViewBinding, V: BaseModel>(
     val viewBinding: T
): RecyclerView.ViewHolder(viewBinding.root) {

    abstract fun bindData(model: V)

}
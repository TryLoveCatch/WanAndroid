package com.tlc.wanandroid.core.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.tlc.wanandroid.core.BaseModel
import com.tlc.wanandroid.data.article.model.Article

open class BaseAdapter(
    protected val context: Context
): RecyclerView.Adapter<BaseViewHolder<out ViewBinding, out BaseModel>>() {
    private var mArrViewHolder = arrayOf<Class<BaseViewHolder<out ViewBinding, out BaseModel>>>()
    protected val mArrData = mutableListOf<BaseModel>()

    fun setData(data: List<BaseModel>?) {
        if (data != null) {
            mArrData.addAll(data)
        }
    }

    fun setViewHolder(vararg clazz: Class<out BaseViewHolder<out ViewBinding, out BaseModel>>) {
        mArrViewHolder = clazz as Array<Class<BaseViewHolder<out ViewBinding, out BaseModel>>>
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out ViewBinding, out BaseModel> {
        return this.mArrViewHolder[viewType].getConstructor(Context::class.java)
            .newInstance(context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out ViewBinding, out BaseModel>, position: Int) {
        onBindViewHolderInner(holder as BaseViewHolder<ViewBinding, in BaseModel>, position)
    }

    override fun getItemCount(): Int {
        return mArrData.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    open fun onBindViewHolderInner(holder: BaseViewHolder<ViewBinding, in BaseModel>, position: Int) {
        holder.bindData(mArrData[position])
    }
}
package com.tlc.wanandroid.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.tlc.wanandroid.core.BaseModel

/**
 * 没有找到合适的统一处理方法
 * 暂时不能用，只能单个用，例如[com.tlc.wanandroid.ui.article.ArticleAdapter]
 *
 * @property mArrData MutableList<BaseModel>
 * @property mArrViewBinding Array<ViewBinding>
 */
class BaseAdapter(): RecyclerView.Adapter<BaseViewHolder<ViewBinding, BaseModel>>() {

    private val mArrData = mutableListOf<BaseModel>()
    private var mArrViewBinding = arrayOf<ViewBinding>()

    fun setData(data: List<BaseModel>) {
        mArrData.addAll(data)
    }

    fun setViewBinding(vararg viewBinding: ViewBinding) {
        mArrViewBinding = viewBinding as Array<ViewBinding>
    }
    
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, BaseModel> {
        TODO("Not yet implemented")
        mArrViewBinding[viewType]

    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, BaseModel>, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}
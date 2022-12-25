package com.tlc.wanandroid.ui.article

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tlc.wanandroid.core.BaseModel
import com.tlc.wanandroid.data.article.model.Article
import com.tlc.wanandroid.databinding.ItemArticleBinding

class ArticleAdapter(
    private val context: Context
): RecyclerView.Adapter<ArticleItemViewHolder>() {
    private val inflate by lazy { LayoutInflater.from(context) }
    private val mArrData = mutableListOf<BaseModel>()

    fun setData(data: List<BaseModel>) {
        mArrData.addAll(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleItemViewHolder {
        return ArticleItemViewHolder(ItemArticleBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        holder.bindData(mArrData[position] as Article)
    }

    override fun getItemCount(): Int {
        return mArrData.size
    }
}
package com.tlc.wanandroid.ui.article

import android.content.Context
import android.view.LayoutInflater
import com.tlc.wanandroid.core.BaseModel
import com.tlc.wanandroid.core.adapter.BaseViewHolder
import com.tlc.wanandroid.data.article.model.Article
import com.tlc.wanandroid.databinding.ItemArticleBinding

class ArticleItemViewHolder(context: Context) : BaseViewHolder<ItemArticleBinding, Article>(
    viewBinding = ItemArticleBinding.inflate(LayoutInflater.from(context))
) {
    override fun bindData(model: Article) {
        viewBinding.tvFirstLevelTitle.text = model.title
    }
}
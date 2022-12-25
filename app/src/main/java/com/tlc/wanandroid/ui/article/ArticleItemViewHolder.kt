package com.tlc.wanandroid.ui.article

import com.tlc.wanandroid.core.adapter.BaseViewHolder
import com.tlc.wanandroid.data.article.model.Article
import com.tlc.wanandroid.databinding.ItemArticleBinding

class ArticleItemViewHolder(viewBinding: ItemArticleBinding) : BaseViewHolder<ItemArticleBinding, Article>(
    viewBinding
) {
    override fun bindData(model: Article) {
        viewBinding.tvFirstLevelTitle.text = model.title
    }
}
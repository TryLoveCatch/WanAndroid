package com.tlc.wanandroid.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.tlc.wanandroid.R
import com.tlc.wanandroid.core.BaseModel
import com.tlc.wanandroid.databinding.RecyclerFooterBinding

class FooterViewHolder(context: Context) : BaseViewHolder<RecyclerFooterBinding, Footer>(
    viewBinding = RecyclerFooterBinding.inflate(LayoutInflater.from(context))
) {
    override fun bindData(model: Footer) {
        if (model.isHasMore) {
            viewBinding.footerPrg.visibility = View.VISIBLE
            viewBinding.footerTxt.setText(R.string.progress_loading)
        } else {
            viewBinding.footerPrg.visibility = View.GONE
            viewBinding.footerTxt.setText(R.string.no_more_data)
        }
    }
}

data class Footer(
    val isHasMore: Boolean,
): BaseModel()
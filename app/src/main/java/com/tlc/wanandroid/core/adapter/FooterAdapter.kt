package com.tlc.wanandroid.core.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.ViewBinding
import com.tlc.wanandroid.core.BaseModel

class FooterAdapter(context: Context): BaseAdapter(context) {
    private var isHasMore = false;

    companion object {
        const val TYPE_FOOTER = Int.MAX_VALUE
    }

    fun setHasMore(isHasMore: Boolean) {
        this.isHasMore = isHasMore
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out ViewBinding, out BaseModel> {
        if (viewType == TYPE_FOOTER) {
            return FooterViewHolder(context)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolderInner(holder: BaseViewHolder<ViewBinding, in BaseModel>, position: Int) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            holder.bindData(Footer(isHasMore))
        } else {
            super.onBindViewHolderInner(holder, position)
        }
    }

    override fun getItemCount(): Int {
        val count = mArrData.size
        return if (count != 0) {
            count + 1
        } else {
            count
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position != 0 && position == itemCount - 1) {
            return TYPE_FOOTER
        }
        return super.getItemViewType(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == TYPE_FOOTER) {
                        layoutManager.spanCount
                    } else {
                        1
                    }
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<out ViewBinding, out BaseModel>) {
        super.onViewAttachedToWindow(holder)
        val lp: ViewGroup.LayoutParams = holder.itemView.layoutParams
        if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
            lp.isFullSpan = holder.itemViewType === TYPE_FOOTER
        }
    }
}
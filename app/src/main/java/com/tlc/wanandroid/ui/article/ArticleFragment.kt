package com.tlc.wanandroid.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tlc.wanandroid.core.BaseFragment
import com.tlc.wanandroid.core.adapter.FooterAdapter
import com.tlc.wanandroid.core.utils.setTitle
import com.tlc.wanandroid.databinding.FragmentArticleBinding
import com.tlc.wanandroid.ui.article.vm.ArticleViewModel

class ArticleFragment : BaseFragment() {

    private val viewBinding: FragmentArticleBinding by lazy {
        FragmentArticleBinding.inflate(layoutInflater)
    }
    private val viewModel: ArticleViewModel by activityViewModels()
    private val adapter by lazy { FooterAdapter(requireContext()) }
    private var page = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun initData() {
        setTitle("文章列表")
        refresh()
        viewModel.articleListLiveData.observe(viewLifecycleOwner, Observer {
            hideEmptyView()
            if (it.errorMsg.isEmpty()) {
                adapter.setHasMore(it.isHasMore)
                adapter.setData(it.articleItems)
                adapter.notifyDataSetChanged()
            } else {
                showError(it.errorMsg)
            }
        })
        adapter.setViewHolder(ArticleItemViewHolder::class.java)
        viewBinding.recyclerView.adapter = adapter
        viewBinding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var lastVisibleItem = -1

                val layoutManager = viewBinding.recyclerView.layoutManager
                lastVisibleItem = if (layoutManager is LinearLayoutManager) {
                    layoutManager.findLastCompletelyVisibleItemPosition()
                } else {
                    -1
                }

                val itemCount: Int = adapter.itemCount
                if (lastVisibleItem >= itemCount - 1 && itemCount > 0 && lastVisibleItem != -1) {
                    loadMore()
                }
            }
        })

        mEmptyView?.setOnRefreshListener {
            refresh()
        }
    }

    override fun initEmptyView() {
        mEmptyView = viewBinding.emptyView
    }

    private fun loadMore() {
        viewModel.fetchArticleList(page++)
    }

    private fun refresh() {
        showLoading()
        page = 0
        viewModel.fetchArticleList(page++)
    }

}
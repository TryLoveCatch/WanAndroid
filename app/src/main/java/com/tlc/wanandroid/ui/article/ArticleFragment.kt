package com.tlc.wanandroid.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tlc.wanandroid.core.adapter.FooterAdapter
import com.tlc.wanandroid.databinding.FragmentArticleBinding
import com.tlc.wanandroid.ui.article.vm.ArticleViewModel

class ArticleFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setViewHolder(ArticleItemViewHolder::class.java)
        page = 0
        viewModel.fetchArticleList(page++)
        viewModel.articleListLiveData.observe(viewLifecycleOwner, Observer {
            if (it.errorMsg.isEmpty()) {
                adapter.setHasMore(it.isHasMore)
                adapter.setData(it.articleItems)
                adapter.notifyDataSetChanged()
            } else {

            }
        })
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

                val itemCount: Int = adapter.getItemCount()
                if (lastVisibleItem >= itemCount - 1 && itemCount > 0 && lastVisibleItem != -1) {
                    loadMore()
                }
            }
        })
    }

    private fun loadMore() {
        viewModel.fetchArticleList(page++)
    }

}
package com.tlc.wanandroid.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.tlc.wanandroid.data.article.model.Article
import com.tlc.wanandroid.databinding.FragmentArticleBinding
import com.tlc.wanandroid.ui.article.vm.ArticleViewModel

class ArticleFragment : Fragment() {

    private val viewBinding: FragmentArticleBinding by lazy {
        FragmentArticleBinding.inflate(layoutInflater)
    }
    private val viewModel: ArticleViewModel by activityViewModels()
    private val adapter by lazy { ArticleAdapter(requireContext()) }
//    private val datas = mutableListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchArticleList(0)
        // TODO: Use the ViewModel
        viewModel.articleListLiveData.observe(viewLifecycleOwner, Observer {
//            datas.clear()
//            datas.addAll(it)
//            adapter.notifyDataSetChanged()
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })
        viewBinding.recyclerView.adapter = adapter;
    }

}
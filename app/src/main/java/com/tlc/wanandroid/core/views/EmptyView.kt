package com.tlc.wanandroid.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.tlc.wanandroid.R

class EmptyView: LinearLayout {
    private var attrs: AttributeSet? = null
    private var topText: String? = ""
    private var canRefresh: Boolean = true
    private var emptyView: View
    private var progressBar: ProgressBar
    private var topTextView: TextView
    private var onClickListenerProxy: OnClickListener
    private var onRefreshListener: OnClickListener? = null

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.attrs = attrs
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        this.attrs = attrs
    }

    init {
        if (attrs != null) {
            var typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView)
            this.topText = typedArray.getString(R.styleable.EmptyView_topText)
            this.canRefresh = typedArray.getBoolean(R.styleable.EmptyView_canRefresh, true)
            typedArray.recycle()
        }
        var inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
        inflater.inflate(R.layout.empty_view, this);
        emptyView = findViewById(R.id.custom_empty_view_emptyview);

        progressBar = findViewById(R.id.custom_empty_view_loading_pb);
        topTextView = findViewById(R.id.custom_empty_view_top_tv);


        setTopText(topText)

        onClickListenerProxy = OnClickListener {
            if (canRefresh && onRefreshListener != null) {
                showLoading()
                onRefreshListener?.onClick(it)
            }
        }

        this.visibility = View.GONE
    }


    private fun setTopText(text: String?) {
        this.topText = text
        this.topTextView.text = text
    }

    fun showError(errorMsg: String) {
        this.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
        setTopText(errorMsg)
        this.setOnClickListener(onClickListenerProxy)
    }

    fun showLoading() {
        this.visibility = View.VISIBLE
        emptyView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        this.setOnClickListener(null)
    }

    fun setOnRefreshListener(onRefreshListener: OnClickListener) {
        this.onRefreshListener = onRefreshListener
    }
}
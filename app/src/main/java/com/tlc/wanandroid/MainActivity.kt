package com.tlc.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tlc.wanandroid.core.utils.showFragment
import com.tlc.wanandroid.ui.article.ArticleFragment
import com.tlc.wanandroid.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }

        showFragment<ArticleFragment>(R.id.container)
    }
}
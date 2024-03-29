package com.tlc.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.baic.icc.annotation.TestAsm
import com.tlc.wanandroid.core.utils.showFragment
import com.tlc.wanandroid.ui.home.HomeFragment

@TestAsm
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }

        showFragment<HomeFragment>(R.id.container)
    }
}
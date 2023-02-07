package com.tlc.wanandroid.ui.home

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.animation.Interpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager
import com.tlc.wanandroid.core.adapter.BaseViewHolder
import com.tlc.wanandroid.databinding.ItemBannerBinding
import com.tlc.wanandroid.ui.home.vm.BannerList
import java.lang.reflect.Field

class BannerViewHolder(context: Context): BaseViewHolder<ItemBannerBinding, BannerList>(
    viewBinding = ItemBannerBinding.inflate(LayoutInflater.from(context))
), ViewPager.OnPageChangeListener {
    companion object {
        // 轮播图每隔2秒滚动一次
        const val TIME = 4 * 1000L
        // 自动滚动的时候 动画只需1秒
        const val TIME_ANIM = 1 * 1000
        val sInterpolator =
            Interpolator { t ->
                var t = t
                t -= 1.0f
                t * t * t * t * t + 1.0f
            }
    }

    private val mHandler = Handler(Looper.getMainLooper())
    private val mAutoPlay = Runnable {
        autoPlay()
        addRunnable()
    }
    private val basePagerAdapter: BannerPagerAdapter by lazy {
        BannerPagerAdapter(context)
    }

    init {
//        val tParams: ViewGroup.LayoutParams = viewBinding.itemBannerViewPager.layoutParams
//        tParams.width = UtilScreen.getScreenWidth()
//        tParams.height = Math.round(tParams.width * 1.0f * 500 / 1080)

        viewBinding.itemBannerViewPager.addOnPageChangeListener(this)
        try {
            val field: Field = viewBinding.itemBannerViewPager::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            val scroller: FixedSpeedScroller =
                FixedSpeedScroller(
                    context,
                    sInterpolator
                )
            field[viewBinding.itemBannerViewPager] = scroller
            scroller.setmDuration(TIME_ANIM)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun bindData(model: BannerList) {

        if (viewBinding.itemBannerViewPager.adapter == null) {
            basePagerAdapter.setData(model.data?.toMutableList())
            viewBinding.itemBannerViewPager.adapter = basePagerAdapter
            viewBinding.itemBannerViewPager.currentItem = 1
            removeRunnable()
            addRunnable()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position > 0 && positionOffset > 0 && positionOffsetPixels > 0) {
            removeRunnable()
        }
    }

    override fun onPageSelected(position: Int) {
        /**
         * 这个会卡顿
         */
//        if (position == 0) {
//            viewBinding.itemBannerViewPager.setCurrentItem(basePagerAdapter.count - 2, false)
//        } else if (position == basePagerAdapter.count - 1) {
//            viewBinding.itemBannerViewPager.setCurrentItem(1, false)
//        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        val position = viewBinding.itemBannerViewPager.currentItem
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            if (position == 0) {
                viewBinding.itemBannerViewPager.setCurrentItem(basePagerAdapter.count - 2, false);
            } else if (position == basePagerAdapter.count - 1) {
                viewBinding.itemBannerViewPager.setCurrentItem(1, false)
            }

            addRunnable()
        } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            if (position == 0) {
                viewBinding.itemBannerViewPager.setCurrentItem(basePagerAdapter.count - 2, false);
            } else if (position == basePagerAdapter.count - 1) {
                viewBinding.itemBannerViewPager.setCurrentItem(1, false)
            }
        } else {

        }
    }

    private fun removeRunnable() {
        mHandler.removeCallbacks(mAutoPlay)
    }

    private fun addRunnable() {
        mHandler.postDelayed(
            mAutoPlay,
            TIME
        )
    }

    private fun autoPlay() {
        val currentItem = viewBinding.itemBannerViewPager.currentItem
        viewBinding.itemBannerViewPager.currentItem = currentItem + 1
    }

    private class FixedSpeedScroller : Scroller {
        private var mDuration = 1500

        constructor(context: Context?) : super(context) {}
        constructor(context: Context?, interpolator: Interpolator?) : super(
            context,
            interpolator
        ) {
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration)
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration)
        }

        fun setmDuration(time: Int) {
            mDuration = time
        }

        fun getmDuration(): Int {
            return mDuration
        }
    }
}
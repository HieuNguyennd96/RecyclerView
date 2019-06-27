package com.example.recyclerview.util

import android.support.v7.widget.RecyclerView


abstract class RecyclerViewScroll : RecyclerView.OnScrollListener() {

    companion object {
        private val HIDE_THRESHOLD = 20f
        private val SHOW_THRESHOLD = 10f
    }

    private var scrollDist = 0
    private var isVisible = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (isVisible && scrollDist > HIDE_THRESHOLD) {
            hide()
            scrollDist = 0
            isVisible = false
        } else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
            show()

            scrollDist = 0
            isVisible = true
        }

        if (isVisible && dy > 0 || !isVisible && dy < 0) {
            scrollDist += dy
        }

    }


    abstract fun show()

    abstract fun hide()

}

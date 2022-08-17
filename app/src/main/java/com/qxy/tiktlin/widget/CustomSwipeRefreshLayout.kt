package com.qxy.tiktlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

//
/**
 * 实现下拉刷新
 * 使用示例：
<androidx.constraintlayout.widget.ConstraintLayout
android:layout_width="match_parent"
android:layout_height="match_parent"
app:layout_behavior="@string/appbar_scrolling_view_behavior">

<com.rdc.may_project.widget.CustomSwipeRefreshLayout
android:layout_width="0dp"
android:layout_height="0dp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintLeft_toLeftOf="parent"
app:onRefreshListener="@{() -> viewModel.onSwipeRefresh()}"
app:refreshing="@{viewModel.swipeRefreshing}" />

<androidx.recyclerview.widget.RecyclerView
android:layout_width="0dp"
android:layout_height="0dp"
android:orientation="vertical"
android:scrollbars="vertical"
app:layoutManager="LinearLayoutManager"
tools:listitem="@layout/item_event" />
</androidx.constraintlayout.widget.ConstraintLayout>
 */

class CustomSwipeRefreshLayout : SwipeRefreshLayout {
    private var startGestureX: Float = 0f
    private var startGestureY: Float = 0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startGestureX = event.x
                startGestureY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                if (Math.abs(event.x - startGestureX) > Math.abs(event.y - startGestureY)) {
                    return false
                }
            }
        }

        return super.onInterceptTouchEvent(event)
    }
}
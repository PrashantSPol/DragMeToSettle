package com.polstech.library.dragme.library;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashant.pol on 8/26/2017.
 */

public class DragLayout extends RelativeLayout {
    private ViewDragHelper mViewDragHelper;
    private List<DragElement> mDragElementList;

    public DragLayout(Context context) {
        super(context, null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mViewDragHelper = ViewDragHelper.create(this, 1.2f, new DragCallback());
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragElementList = new ArrayList<>();
        crawlMore(this);
    }

    private void crawlMore(ViewGroup viewGroup) {
        Log.i("CheckCheck", "ele " + viewGroup);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);

            if(child instanceof DragElement) {
                Log.i("CheckCheck", "Got child dragelement " + child);
                mDragElementList.add((DragElement) child);
            } else if (child instanceof ViewGroup) {
                Log.i("CheckCheck", "Got child other " + child);
                crawlMore((ViewGroup) child);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if(action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mViewDragHelper.cancel();
            return true;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private class DragCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            for(DragElement dragElement : mDragElementList) {
                if(child == dragElement) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            int leftBound = getPaddingLeft();
            int rightBound = getWidth() - child.getWidth();
            return Math.min(Math.max(left, leftBound), rightBound);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int topBound = getPaddingTop();
            int bottomBound = getHeight() - child.getHeight();
            return Math.min(Math.max(top, topBound), bottomBound);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.i("CheckCheck", "View released ");
            DragElement dragElement = (DragElement) releasedChild;
            mViewDragHelper.smoothSlideViewTo(dragElement, (int)dragElement.getRestElement().getX(), (int)dragElement.getRestElement().getY());
            ViewCompat.postInvalidateOnAnimation(DragLayout.this);
        }
    }
}

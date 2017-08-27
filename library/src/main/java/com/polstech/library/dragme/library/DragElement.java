package com.polstech.library.dragme.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by prashant.pol on 8/26/2017.
 */

public class DragElement extends LinearLayout {
    private RestElement mRestElement;
    private int resourceId;

    public DragElement(Context context) {

        super(context);
        Log.i("CheckCheck", "con 1");
    }

    public DragElement(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("CheckCheck", "con 2");
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DragElement, 0, 0);
        try {
            resourceId = typedArray.getResourceId(R.styleable.DragElement_restLayout, 0);

        } finally {
            typedArray.recycle();
        }
    }

    public DragElement(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("CheckCheck", "con 3");

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mRestElement = (RestElement) (getRootView()).findViewById(resourceId);
    }

    public RestElement getRestElement() {
        return mRestElement;
    }

    public void setRestElement(RestElement mRestElement) {
        this.mRestElement = mRestElement;
    }
}

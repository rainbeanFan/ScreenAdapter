package com.rainbean.screenadapter;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomLinearLayout extends LinearLayout {

//    防止omMeasure方法多次调用，导致多次缩放。
    private boolean flag = true;

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (flag){
            float scaleX = UIUtils.getInstance(getContext()).getHorizontalScaleValue();
            float scaleY = UIUtils.getInstance(getContext()).getVerticalScaleValue();

            int childrenCount = getChildCount();
            for (int i = 0; i < childrenCount; i++) {
                LayoutParams layoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
                layoutParams.width = (int) (layoutParams.width*scaleX);
                layoutParams.height = (int)(layoutParams.height*scaleY);
                layoutParams.leftMargin = (int)(layoutParams.leftMargin*scaleX);
                layoutParams.rightMargin = (int)(layoutParams.rightMargin*scaleX);
                layoutParams.topMargin = (int)(layoutParams.topMargin*scaleY);
                layoutParams.bottomMargin = (int)(layoutParams.bottomMargin*scaleY);
            }
        }
        flag = false;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }
}

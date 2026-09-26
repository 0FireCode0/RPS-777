package org.top.economic;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScrollableBottomNavigationView extends BottomNavigationView {

    private static final int MAX_ITEM_COUNT = 20;
    private static final int ITEM_WIDTH_DP = 64;

    public ScrollableBottomNavigationView(Context context) {
        super(context);
    }

    public ScrollableBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableBottomNavigationView(
            Context context,
            AttributeSet attrs,
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getMaxItemCount() {
        return MAX_ITEM_COUNT;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int itemCount = getMenu().size();

        int itemWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                ITEM_WIDTH_DP,
                getResources().getDisplayMetrics()
        );

        int menuWidth = itemCount * itemWidth;

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        int finalWidth;

        if (mode == MeasureSpec.EXACTLY) {
            finalWidth = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            finalWidth = Math.max(size, menuWidth);
        } else {
            finalWidth = menuWidth;
        }

        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                finalWidth,
                MeasureSpec.EXACTLY
        );

        super.onMeasure(newWidthMeasureSpec, heightMeasureSpec);
    }
}
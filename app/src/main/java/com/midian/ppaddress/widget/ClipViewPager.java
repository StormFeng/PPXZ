package com.midian.ppaddress.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.ui.company.CompanyDetialActivity;
import com.midian.ppaddress.ui.main.HomeFragment;
import com.midian.ppaddress.ui.main.MainActivity;

import midian.baselib.utils.UIHelper;

/**
 * Created by HanHailong on 15/9/27.
 */
public class ClipViewPager extends ViewPager {
    public static int index;
    private boolean scrollble = true;
    private View v1,v2;//手指按下和抬起时的View

    public ClipViewPager(Context context) {
        super(context);
    }

    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            v1=viewOfClickOnScreen(ev);
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
           v2 = viewOfClickOnScreen(ev);
            if(v1==v2) {
                if (v2 != null) {
                    index = indexOfChild(v2);
                    if (getCurrentItem() != index) {
                        setCurrentItem(indexOfChild(v2));
                    }
                }
            }
        }
//        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            View view = viewOfClickOnScreen(ev);
//            if (view != null) {
//                index = indexOfChild(view);
//                Log.d("wqf",index+"<<index<<"+getCurrentItem()+"<<getCurrentItem");
//                if (getCurrentItem() != index) {
//                    setCurrentItem(indexOfChild(view));
//                }
//            }
//        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param ev
     * @return
     */
    private View viewOfClickOnScreen(MotionEvent ev) {
        int childCount = getChildCount();
        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            v.getLocationInWindow(location);
            int minX = location[0];
            int minY = getTop();

            int maxX = location[0] + v.getWidth();
            int maxY = getBottom();
            float x = ev.getRawX();
            float y = ev.getY();
//            Log.d("wqf","i::"+i);
//            Log.d("wqf","minX::"+minX+">>minY::"+minY+">>maxX::"+maxX+">>maxY::"+maxY+">>x::"+x+">>y::"+y);
            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }

}

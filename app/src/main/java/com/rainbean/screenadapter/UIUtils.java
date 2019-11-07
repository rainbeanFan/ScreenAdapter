package com.rainbean.screenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtils {


    public static final String DIMEN_CLASS = "com.android.internal.R$dimen";

    Context mContext;
//  设计稿中提供的标准尺寸
    public static final float STANDARD_WIDTH = 1080f;
    public static final float STANDARD_HEIGHT = 1920f;
//  设备实际的尺寸
    public static float displayMetricsWidth;

    public static float displayMetricsHeight;

    private static UIUtils instance;

    public static UIUtils getInstance(Context context){

      return new UIUtils(context);
    }


    private UIUtils(Context context) {
        mContext = context;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
//      得到设备真实的宽和高
        if (displayMetricsWidth == 0f|| displayMetricsHeight==0f){
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
//            得到状态栏的高度
            int systemBarHeight = getSystemBarHeight(mContext);
//            横竖屏情况
            if (displayMetrics.widthPixels>displayMetrics.heightPixels){
//                横屏
                displayMetricsWidth = displayMetrics.heightPixels;
                displayMetricsHeight = displayMetrics.widthPixels - systemBarHeight;
            }else {
                displayMetricsWidth = displayMetrics.widthPixels;
                displayMetricsHeight = displayMetrics.heightPixels - systemBarHeight;
            }
        }




    }

    private int getSystemBarHeight(Context context) {
        return getValue(context,DIMEN_CLASS,"system_bar_height",48);
    }

    private int getValue(Context context, String dimenClass,
                         String system_bar_height, int defaultHeight) {
        try {
            Class<?> c = Class.forName(dimenClass);
            Object o = c.newInstance();
            Field field = c.getField(system_bar_height);
            int i = Integer.parseInt(field.get(o).toString());
            return context.getResources().getDimensionPixelSize(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return defaultHeight;
    }



    public float getHorizontalScaleValue(){
        return (float) displayMetricsWidth/STANDARD_WIDTH;
    }

    public float getVerticalScaleValue(){
        return (float) displayMetricsHeight/(STANDARD_HEIGHT - getSystemBarHeight(mContext));
    }

}

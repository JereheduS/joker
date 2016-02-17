package com.example.asus.mdcommunity.util;

import android.content.Context;

/**
 * Created by asus on 2016/2/17.
 */
public class DpAndPxUtil {
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}

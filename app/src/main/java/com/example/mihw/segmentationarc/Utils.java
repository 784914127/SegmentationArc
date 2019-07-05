package com.example.mihw.segmentationarc;

import android.content.res.Resources;

public class Utils {
    public static int dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}

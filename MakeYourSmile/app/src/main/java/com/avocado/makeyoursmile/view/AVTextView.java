package com.avocado.makeyoursmile.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HDlee on 11/16/15.
 */
public class AVTextView extends TextView{


    public AVTextView(Context context) {
        super(context);
    }

    public AVTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AVTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AVTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}

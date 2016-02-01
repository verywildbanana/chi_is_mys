package com.avocado.makeyoursmile.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by HDlee on 11/16/15.
 */
public class AVEditText extends EditText{


    public AVEditText(Context context) {
        super(context);
    }

    public AVEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AVEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AVEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

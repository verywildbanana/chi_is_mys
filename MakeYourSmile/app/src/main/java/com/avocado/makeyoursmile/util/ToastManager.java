package com.avocado.makeyoursmile.util;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.avocado.makeyoursmile.Constants;


public class ToastManager {
	private static final String TAG = "ToastManager";

	private static ToastManager mInstance = null;
	private Context mContext;
	private Toast _mToast = null;
	private Toast mCustomToast = null;
	private Handler mHandler;

	static {
		mInstance = new ToastManager();
	}

	ToastManager() {

		mHandler = new Handler();
	}

	public static ToastManager getInstance() {
		return mInstance;
	}

	public void init(Context context) {
		mContext = context;
        _mToast = Toast.makeText(mContext, null, Toast.LENGTH_SHORT);

		if (_mToast.getView() != null && _mToast.getView() instanceof ViewGroup) {
			ViewGroup vg = (ViewGroup)_mToast.getView();
			int childCount = vg.getChildCount();
			for (int j = 0; j < childCount; j++) {
				View child = vg.getChildAt(j);
				if (child != null &&child instanceof TextView) {
					((TextView)child).setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
				}
			}
		} else if (_mToast.getView() != null && _mToast.getView() instanceof TextView) {
			((TextView) _mToast.getView()).setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
		}
	}
	
	public boolean isInit() {

		return mContext != null;
	}

	public void show(int resId) {

        show(resId, Toast.LENGTH_LONG);
	}

	public void show(CharSequence text) {

		show(text, Toast.LENGTH_LONG);
	}
	
	public void showDelay(final CharSequence text) {

        mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {

				show(text, Toast.LENGTH_SHORT);
			}
		}, 2000);
	}

	public void show(int resId, int duration) {
        _mToast.setText(resId);
		_mToast.setDuration(duration);
		_mToast.show();
	}

	public void debug(CharSequence text) {

        debug(text, Toast.LENGTH_SHORT);
	}

	public void debug(CharSequence text, int duration) {

		if (Constants.RELEASE_BUILD == false) {
			show(text, duration);
		}
	}

	public void show(CharSequence text, int duration) {
		_mToast.setText(text);
		_mToast.setDuration(duration);
		_mToast.show();
	}

	public void show(String text, int duration) {
        _mToast.setText(text);
		_mToast.setDuration(duration);
		_mToast.show();
	}

	public void show(String text, int duration, int gravity) {
        _mToast.setText(text);
        _mToast.setDuration(duration);
        _mToast.setGravity(gravity, 0, 0);
		_mToast.show();
	}
}

package com.avocado.makeyoursmile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.avocado.makeyoursmile.Constants;
import com.avocado.makeyoursmile.util.SmartLog;

public class AVToggleButton extends ImageView {

	private static final String TAG = ToggleButton.class.getSimpleName();

	private boolean mSelected = false;

	private OnOffButtonListener mListener;

	int mOnImageResource;
	int mOffImageResource;
	public boolean toggleButtonClickable = true;

	Object mTag;

	public AVToggleButton(Context context) {
		super(context);

		init();
	}

	public AVToggleButton(Context context, AttributeSet attrs) {

		super(context, attrs);

		init();
		initAttr(attrs);
	}

	private void init() {

		setClickable(true);
		setScaleType(ScaleType.CENTER);
		setOnClickListener(mClickListener);
	}

	private void initAttr(AttributeSet attrs) {

		int ToggleButtonOnImage = attrs.getAttributeResourceValue(
				Constants.AVOCADO_SCHEME, "ToggleButtonOnImage", 0);
		int ToggleButtonOffImage = attrs.getAttributeResourceValue(
				Constants.AVOCADO_SCHEME, "ToggleButtonOffImage", 0);

		SmartLog.getInstance().w(TAG,
				"on:" + ToggleButtonOnImage + "  off:" + ToggleButtonOffImage);

		setToggleImage(ToggleButtonOnImage, ToggleButtonOffImage);

		toggleButtonClickable = attrs.getAttributeBooleanValue(
				Constants.AVOCADO_SCHEME, "ToggleButtonClickable", true);

		if (!toggleButtonClickable) {
			setOnClickListener(null);
			setClickable(false);
		}
	}

	public void setToggleClickable(boolean clickable) {

		toggleButtonClickable = clickable;
		if (!toggleButtonClickable) {
			setOnClickListener(null);
			setClickable(false);
		} else {
			setOnClickListener(mClickListener);
			setClickable(true);
		}

	}

	public void setToggleImage(int on, int off) {

		mOnImageResource = on;
		mOffImageResource = off;

		setImageResource(mOffImageResource);

	}

	@Override
	public void setSelected(boolean selected) {

		mSelected = selected;

		if (mSelected) {
			setImageResource(mOnImageResource);
		} else {
			setImageResource(mOffImageResource);
		}
	}
	
	public boolean getSelected() {
		
		return mSelected;
	}

	public void setOnToggleListener(OnOffButtonListener listener) {
		mListener = listener;
	}

	public void setToggleListener(Object tag, OnOffButtonListener listener) {
		mTag = tag;
		mListener = listener;
	}

	public void setOn(boolean on) {
		mSelected = on;

		this.setSelected(mSelected);
	}

	public boolean isOn() {
		return mSelected;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (isEnabled() && toggleButtonClickable) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				// setSelected(!isSelected());
				mSelected = !mSelected;
			}

			boolean result = super.onTouchEvent(event);

			if (event.getAction() == MotionEvent.ACTION_UP) {
				this.setSelected(mSelected);
			}

			return result;
		} else {
			return super.onTouchEvent(event);
		}
	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (mListener != null) {

				if (mTag != null) {

					v.setTag(mTag);
				}

				mListener.onToggled(v, mSelected);
			}
		}
	};

	public interface OnOffButtonListener {
		
		void onToggled(View v, boolean on);
	}
}
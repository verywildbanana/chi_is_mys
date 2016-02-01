package com.avocado.makeyoursmile.util;

import android.util.Log;

import com.avocado.makeyoursmile.Constants;


public class SmartLog {
	private static SmartLog sInstance;

	public static SmartLog getInstance() {
		if (sInstance == null) {
			sInstance = new SmartLog();
		}
		return sInstance;
	}

	private SmartLog() {
		mState = LE_DEBUG;// LE_DEBUG;
	}

	private final int LE_VERVE = 1;
	private final int LE_DEBUG = 2;
	private final int LE_INFO = 3;
	private final int LE_WARNING = 4;
	private final int LE_ERROR = 5;
	private final int LE_FATAL = 6;
	private final int LE_NONE = 10;

	private int mState;

	public void SetLogLevel(String level) {
		if (level == "v")
			mState = LE_VERVE;
		else if (level.equals("V"))
			mState = LE_VERVE;
		else if (level.equals("d"))
			mState = LE_DEBUG;
		else if (level.equals("D"))
			mState = LE_DEBUG;
		else if (level.equals("i"))
			mState = LE_INFO;
		else if (level.equals("I"))
			mState = LE_INFO;
		else if (level.equals("w"))
			mState = LE_WARNING;
		else if (level.equals("W"))
			mState = LE_WARNING;
		else if (level.equals("e"))
			mState = LE_ERROR;
		else if (level.equals("E"))
			mState = LE_ERROR;
		else if (level.equals("f"))
			mState = LE_FATAL;
		else if (level.equals("F"))
			mState = LE_FATAL;
		else if (level.equals("n"))
			mState = LE_NONE;
		else if (level.equals("N"))
			mState = LE_NONE;
	}

	public void v(String TAG, String msg) {

		if (Constants.IS_SHOW_SMART_LOG == true) {
			if (mState <= LE_VERVE) {
				Log.v("[" + TAG + "]", msg);
			}
		}
	}

	public void d(String TAG, String msg) {
		if (Constants.IS_SHOW_SMART_LOG == true) {
			if (mState <= LE_DEBUG) {
//				Log.d("[" + TAG + "]", msg);
				if (msg.length() > 4000) {
					int chunkCount = msg.length() / 4000;     // integer division
					for (int i = 0; i <= chunkCount; i++) {
						int max = 4000 * (i + 1);
						if (max >= msg.length()) {
							Log.d(TAG, "[" + TAG + " " + i + "/" + chunkCount + "]" + msg.substring(4000 * i));
						} else {
							Log.d(TAG, "[" + TAG + " " + i + "/" + chunkCount + "]" + msg.substring(4000 * i, max));
						}
					}
				} else {
					Log.d("[" + TAG + "]", msg);
				}
			}
		}
	}

	public void i(String TAG, String msg) {
		if (Constants.IS_SHOW_SMART_LOG == true) {
			if (mState <= LE_INFO) {
//				Log.i("[" + TAG + "]", msg);
				if (msg.length() > 4000) {
					int chunkCount = msg.length() / 4000;     // integer division
					for (int i = 0; i <= chunkCount; i++) {
						int max = 4000 * (i + 1);
						if (max >= msg.length()) {
							Log.i(TAG, "[" + TAG + " " + i + "/" + chunkCount + "]" + msg.substring(4000 * i));
						} else {
							Log.i(TAG, "[" + TAG + " " + i + "/" + chunkCount + "]" + msg.substring(4000 * i, max));
						}
					}
				} else {
					Log.i("[" + TAG + "]", msg);
				}
			}
		}
	}

	public void w(String TAG, String msg) {
		if (Constants.IS_SHOW_SMART_LOG == true) {
			if (mState <= LE_WARNING) {
//				Log.w("[" + TAG + "]", msg);
				if (msg.length() > 4000) {
					int chunkCount = msg.length() / 4000;     // integer division
					for (int i = 0; i <= chunkCount; i++) {
						int max = 4000 * (i + 1);
						if (max >= msg.length()) {
							Log.w(TAG, "[" + TAG + " " + i + "/" + chunkCount + "]" + msg.substring(4000 * i));
						} else {
							Log.w(TAG, "[" + TAG + " " + i + "/" + chunkCount + "]" + msg.substring(4000 * i, max));
						}
					}
				} else {
					Log.w("[" + TAG + "]", msg);
				}
			}
		}
	}

	public void e(String TAG, String msg) {
		if (Constants.IS_SHOW_SMART_LOG == true) {
			if (mState <= LE_ERROR) {
				Log.e("[" + TAG + "]", msg);
			}
		}
	}

	public void f(String TAG, String msg) {
		if (Constants.IS_SHOW_SMART_LOG == true) {
			if (mState <= LE_FATAL) {
				Log.e("[FATAL][" + TAG + "]", msg);
			}
		}
	}
	
	public void exception(Exception e) {
		
		if (Constants.IS_SHOW_SMART_LOG == true) {
			if (mState <= LE_ERROR) {
				e.printStackTrace();
			}
		}
	}
}

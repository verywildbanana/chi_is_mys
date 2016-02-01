package com.avocado.makeyoursmile.util;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

public class SpannableStringUtil {

	public static void setColorSpan(SpannableString str, String spanStr,
			int color) {
		try {
			String ori = str.toString();

			int f = ori.indexOf(spanStr);
			int l = f + spanStr.length();
			str.setSpan(new ForegroundColorSpan(color), f, l, 0);
		} 
		catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void setStyleSpan(SpannableString str, String spanStr,
									StyleSpan style) {
		try {
			String ori = str.toString();

			int f = ori.indexOf(spanStr);
			int l = f + spanStr.length();
			str.setSpan(style, f, l, 0);
		}
		catch (Exception e) {
			// e.printStackTrace();
		}
	}
	
	public static void setUnderlineSpan(SpannableString str, String spanStr) {
		try {
			String ori = str.toString();

			int f = ori.indexOf(spanStr);
			int l = f + spanStr.length();
			
			str.setSpan(new UnderlineSpan(), f, l, 0);
		} 
		catch (Exception e) {
			// e.printStackTrace();
		}
	}
}

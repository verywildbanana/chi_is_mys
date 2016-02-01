package com.avocado.makeyoursmile.util;

import android.content.res.Resources;

public class DensityUtil {

	public static int densityToPixel(Resources r, float dipValue) {

		return (int) (dipValue * r.getDisplayMetrics().density + 0.5f);
	}
}

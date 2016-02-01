package com.avocado.makeyoursmile;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.avocado.makeyoursmile.util.SharedPreferenceManager;
import com.avocado.makeyoursmile.util.SmartLog;

import java.util.UUID;


public class Global {

	private final String TAG = Global.class.getSimpleName();

	private static Context mApplicationContext;

	public String mClientVersion = null;
	private static Global sInstance = null;
	private String mMdn;
	private String mSimpleMdn = "";
	private int mDisplayWidth = 0;
	private int mDisplayHeight;
	public String mOsver;
	private String mModel;
	public String mAppver;
	public String mDeviceID;
	public int mSdkInt;
	private String mCountryIso;

	static {
		sInstance = new Global();
	}

	public Global() {

	}

	public static Global getInstance() {

		return sInstance;
	}

	public void setApplicationContext(Context applicationContext) {
		Global.mApplicationContext = applicationContext;
	}

	public Context getApplicationContext() {
		return mApplicationContext;
	}


	public int getDisplayWidth() {

		if (mDisplayWidth == 0) {

			DisplayMetrics dm = mApplicationContext.getResources().getDisplayMetrics();

			int width = dm.widthPixels;
			mDisplayWidth = width;

		}

		SmartLog.getInstance().w(TAG, "width "+mDisplayWidth);

		return mDisplayWidth;
	}



	public int getDisplayHeight() {

		if(mDisplayHeight == 0) {

			DisplayMetrics dm = mApplicationContext.getResources().getDisplayMetrics();

			int height = dm.heightPixels;
			mDisplayHeight = height;


		}

		SmartLog.getInstance().w(TAG, "Height "+mDisplayHeight);

		return mDisplayHeight;

	}

	public boolean isFullHd() {


		if (getDisplayWidth() >= 1080) {

			return true;

		}

		return false;


	}

	public float dipToPixels(float dipValue) {

		DisplayMetrics dm = mApplicationContext.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, dm);
	}



	public String getMdn() {
		return mMdn;
	}

	public void setMdn(String mdn) {

		mMdn = mdn;
		setSimpleMdn(mdn);
	}

	public void setSimpleMdn(String mdn) {

		if(TextUtils.isEmpty(mdn)) {
			mSimpleMdn = "";
		}
		else {
			if(mdn.contains("+82")) {

				mSimpleMdn = mdn.replace("+82", "0");
			}
			else {

				mSimpleMdn = mdn;
			}
		}
	}

	public String getSimpleMdn() {

		return mSimpleMdn;
	}


	public String getCountryIs() {

		if(mCountryIso == null) {

			final TelephonyManager tm = (TelephonyManager) mApplicationContext.getSystemService(Context.TELEPHONY_SERVICE);
			mCountryIso = tm.getSimCountryIso();


		}

		return mCountryIso;
	}

	public String getDeviceUUID() {


		if(mDeviceID == null) {

			final TelephonyManager tm = (TelephonyManager) mApplicationContext.getSystemService(Context.TELEPHONY_SERVICE);
			final String tmDevice, tmSerial, androidId;
			tmDevice = "" + tm.getDeviceId();
			tmSerial = "" + tm.getSimSerialNumber();
			androidId = "" + android.provider.Settings.Secure.getString(mApplicationContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

			UUID deviceUuid = new UUID(tmDevice.hashCode(), ((long)androidId.hashCode() << 32) | tmSerial.hashCode());

			if(Constants.RELEASE_BUILD) {

				mDeviceID = deviceUuid.toString();
			}
			else {

				mDeviceID = (new StringBuffer(deviceUuid.toString())).reverse().toString();  
			}
		}

		return mDeviceID;
	}

	public String getOsver() {

		if(mOsver == null) {

			mOsver = android.os.Build.VERSION.RELEASE;
			SmartLog.getInstance().w(TAG, "mOsver "+mOsver);

		}


		return mOsver;

	}


	public int getSdkInt() {

		if(mSdkInt == 0) {

			mSdkInt = android.os.Build.VERSION.SDK_INT;
			SmartLog.getInstance().w(TAG, "mSdkInt "+mSdkInt);

		}


		return mSdkInt;
	}

	public String getModel() {

		if(mModel == null) {

			mModel = android.os.Build.MODEL;
			SmartLog.getInstance().w(TAG, "mModel "+mModel);
		}
		return mModel;
	}

	public String getPackageName() {
		
		return mApplicationContext.getPackageName();
	}
	
	public String getAppVer() {

		if(mAppver == null) {

			PackageManager manager = mApplicationContext.getPackageManager();
			PackageInfo info = null;
			try {
				info = manager.getPackageInfo(mApplicationContext.getPackageName(), 0);
			} catch (PackageManager.NameNotFoundException e1) {

				//				e1.printStackTrace();
				SmartLog.getInstance().e(TAG, e1.getMessage());
			}
			mAppver = info.versionName;

			SmartLog.getInstance().w(TAG, "mAppver "+mAppver);
		}

		return mAppver;
	}


	public boolean isNumeric(String str)
	{                          
		return str.matches("^-?\\d+\\.?\\d*$");
	}

	/**
	 * 특정 앱 설치 여부 확인
	 * @param context
	 * @param pkgName
	 * @return
	 */
	public boolean isInstalledApp(Context context, String pkgName) {
		PackageManager pm = context.getPackageManager();

		String vName = null;
		ApplicationInfo aInfo = null;

		try
		{
			aInfo = pm.getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
			PackageInfo pInfo = pm.getPackageInfo(pkgName, 0);
			vName = pInfo.versionName;
		}
		catch(PackageManager.NameNotFoundException e) {

			// e.printStackTrace();
			SmartLog.getInstance().e(TAG, "NameNotFoundException");
			return false;
		}

		if(pkgName.equals(aInfo.packageName)){
			return true;
		} else {
			return false;
		}
	}


	public double getDefaultLatitude() {


		return 37.4018902;
	}



	public double getDefaultLongitude() {



		return 127.1101719;
	}

	public String getEndpoint() {


		if(Constants.RELEASE_BUILD) {

			return Constants.API_URL_LIVE;
		}
		else {

			return Constants.API_URL_DEV;

		}

	}


	public void initUserInfo() {


		SharedPreferenceManager.getInstance().setUserId(0);
		SharedPreferenceManager.getInstance().setBackgroundService(false);
		SharedPreferenceManager.getInstance().setGCMId(null);
	}
}

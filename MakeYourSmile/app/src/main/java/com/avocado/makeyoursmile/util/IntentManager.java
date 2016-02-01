package com.avocado.makeyoursmile.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;

import com.avocado.makeyoursmile.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class IntentManager {

	public static String TAG = IntentManager.class.getSimpleName().toString();

	private static IntentManager sInstance;

	private Handler mHandler;

	/**
	 * Comment : EXTRA KEY 값은 PkIntentManager에 공통으로 관리 합니다.
	 */

	//공통
	public static final String EXTRA_TEXT						= "TEXT";		// 일반 String
	public static final String EXTRA_TEXT_LIST					= "TEXT_LIST";	// String ArrayList
	public static final String EXTRA_NUMBER						= "NUMBER";
	public static final String EXTRA_BOOLEAN					= "BOOLEAN";
	public static final String EXTRA_IMAGE						= "IMAGE";		// ImageData
	public static final String EXTRA_IMAGE_LIST					= "IMAGE_LIST";	// ImageDatas
	public static final String EXTRA_TITLE						= "EXTRA_TITLE";	// Acitity Title
	public static final String EXTRA_TYPE						= "TYPE";
	public static final String EXTRA_CALL_NUMBER				= "CALL_NUMBER";
	public static final String EXTRA_ID							= "ID";
	public static final String EXTRA_GCM						= "GCM";
	public static final String EXTRA_POSITION			   	    = "POSITION";

	/**
	 * Comment : Request code 값은 PkIntentManager에 공통으로 관리 합니다.
	 */
	public static final int REQ_CODE_IMAGE_LOAD					= 2701; // 이미지 불러오기 공통
	public static final int REQ_CODE_CAMERA						= 2702; // 카메라
	public static final int REQ_CODE_USER_INFO    				= 2703; // 유저 정보
	public static final int REQ_CODE_USER_EDIT    		        = 2704; // 유저 정보 수정
	public static final int REQ_CODE_MAIN						= 2707; // 메인으로 이동
	public static final int REQ_CODE_PHOTO_ALBUM				= 2708; // 사진 앨범 이동
	public static final int REQ_CODE_FAKE						= 2709; // 속이기 이동

	private Activity mLastActivity;
	private ArrayList<ClassMap> mClassArray = new ArrayList<ClassMap>();
	private ArrayList<ExtraParam> mExtras = new ArrayList<ExtraParam>();

	private boolean mBlock = false; // 에니메이션 중 인텐트 요청 방지
	private int mIntentAniTime = 400;
	public static IntentManager getInstance() {
		if (sInstance == null) {
			sInstance = new IntentManager();
		}
		return sInstance;
	}

	public IntentManager() {

		mHandler = new Handler();
	}

	/**
	 * Comment : EXTRAS 데이터를 연속적으로 사용할 경우 초기화 시키지 않는다.
	 */
	private void init() {

		mExtras.clear();
	}

	private void log(String tag) {

		if (Constants.CRASH_REPORT_LOGGER == false) {

			SmartLog.getInstance().w(TAG, tag + "IntentManager log size " + mClassArray.size());

			int size = mClassArray.size();
			for (int i = 0; i < size ; i++) {

				SmartLog.getInstance().w(TAG, tag + "IntentManager log " + mClassArray.get(i).simpleName);
			}
		}
	}

	public String getForeGroundActivityName() {


		if (mClassArray != null
				&& mClassArray.isEmpty() == false) {

			return mClassArray.get(mClassArray.size()-1).simpleName;

		}

		return null;

	}

	/**
	 * Comment : Intent startActivity
	 *
	 * @param initExtraData
	 *            Extras 초기화
	 */
	public void push(final Activity currentAct, Class<?> cls, boolean initExtraData) {

		if (mBlock) {

			SmartLog.getInstance().d(TAG, "push() is block:");
			return;
		}


		final Intent intent = set(currentAct, cls, initExtraData);
		log("push");
		currentAct.startActivity(intent);
	}


	public void push(final Activity currentAct, Class<?> cls, boolean initExtraData, int flags, final boolean transition) {

		if (mBlock) {

			SmartLog.getInstance().d(TAG, "pushAddFlags() is block:");
			return;
		}

		final Intent intent = set(currentAct, cls, initExtraData);
		log("push");

		if (flags > 0) {

			intent.addFlags(flags);

		}
		currentAct.startActivity(intent);

		if (transition) {


		}
		else {

			currentAct.overridePendingTransition(0, 0);

		}
	}


	/**
	 * Comment : Intent startActivityForResult
	 *
	 * @param initExtraData
	 *            Extras 초기화
	 */
	public void pushForResult(final Activity currentAct, Class<?> cls, final int requestCode, boolean initExtraData, final boolean transition, int flags) {

		if (mBlock) {

			SmartLog.getInstance().d(TAG, "pushForResult() is block:");
			return;
		}

		final Intent intent = set(currentAct, cls, initExtraData);

		log("pushForResult");

		if (flags > 0) {

			intent.addFlags(flags);
		}
		currentAct.startActivityForResult(intent, requestCode);

		if (transition) {
			currentAct.overridePendingTransition(0, 0);

		}
		else {

			currentAct.overridePendingTransition(0, 0);

		}

	}


	public void pushForResult(final Activity currentAct, Class<?> cls, final int requestCode, boolean initExtraData, int flags, final int anim_enter, final int anim_exit) {

		if (mBlock) {

			SmartLog.getInstance().d(TAG, "pushForResult() is block:");
			return;
		}

		final Intent intent = set(currentAct, cls, initExtraData);

		log("pushForResult");

		if (flags > 0) {

			intent.addFlags(flags);
		}

		currentAct.startActivityForResult(intent, requestCode);

	}

	/**
	 * @Method Name : doCallToNumber
	 * @author mangosteen@kiwiple.com
	 * @date : 2014. 1. 28.
	 * Desc : 전화걸기
	 * @param currentAct
	 * @param phoneNumber : 전화번호(String)
	 */
	public void doCallToNumber(Activity currentAct, String phoneNumber) {

		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
		currentAct.startActivity(intent);
	}

	/**
	 * @Method Name : doLinkWebUrl
	 * @author mangosteen@kiwiple.com
	 * @date : 2014. 1. 28.
	 * Desc : 외부 브라우저로 연결
	 * @param currenrAct
	 * @param url : web url (String)
	 */
	public void doLinkWebUrl(Activity currenrAct, String url) {

		StringBuffer sb = new StringBuffer();
		if(url != null && url.length() > 0) {

			if(url.contains("https://") || url.contains("http://")) {
				sb.append(url);
			}
			else {
				sb.append("http://").append(url);
			}

			String checkedUrl = sb.toString();

			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(checkedUrl));
			currenrAct.startActivity(intent);
		}
	}

	/**
	 * @Method Name : pushWithActionForResult
	 * @author mangosteen@kiwiple.com
	 * @date : 2014. 1. 22.
	 * Desc : 사진촬영과 같은 Action Value를 이용한 Intent 실행
	 * @param currentAct
	 * @param action
	 * @param requestCode
	 */
	public void pushWithActionForResult(Activity currentAct, String action,
			int requestCode) {

		if (mBlock) {

			SmartLog.getInstance().d(TAG, "pushWithActionForResult() is block:");
			return;
		}


		Intent intent = set(currentAct, action);
		log("pushWithActionForResult");
		currentAct.startActivityForResult(intent, requestCode);

	}

	private Intent set(Activity currentAct, Class<?> cls, boolean initExtraData) {

		mBlock = true;
		SmartLog.getInstance().d(TAG, "set() : mBlock:" + mBlock);

		SmartLog.getInstance().d(TAG, "from : " + currentAct.toString());
		SmartLog.getInstance().d(TAG, "to : " + cls.getSimpleName());

		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {

				mBlock = false;
				SmartLog.getInstance().d(TAG, "set(activity) delay : mBlock:" + mBlock);
			}
		}, mIntentAniTime);

		mClassArray.add(new ClassMap(cls.getSimpleName().toString(), cls));

		Intent intent = new Intent(currentAct, cls);

		SmartLog.getInstance().d(TAG, "set() intent or action : " + cls.getSimpleName().toString());

		if (mExtras.size() > 0) {
			for (ExtraParam param : mExtras) {

				SmartLog.getInstance().d(TAG, "ExtraParam name:" + param.name + ", value:" + param.value);
				param.put(intent);
			}
		}
		else {
			SmartLog.getInstance().d(TAG, "NoExtra ");
		}

		if (initExtraData) {

			init();
		}

		return intent;
	}

	/**
	 *
	 * @Method Name : set
	 * @author mangosteen@kiwiple.com
	 * @date : 2014. 1. 22.
	 * Desc : Action String을 이용한 Intent 실행
	 * @param currentAct
	 * @param action
	 * @return
	 */

	private Intent set(Activity currentAct, String action) {

		mBlock = true;
		SmartLog.getInstance().d(TAG, "set(action) : mBlock:" + mBlock);

		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {

				mBlock = false;
				SmartLog.getInstance().d(TAG, "set(action) delay : isBlock:" + mBlock);
			}
		}, mIntentAniTime);

		mClassArray.add(new ClassMap(action, null));

		Intent intent = new Intent(action);

		if (mExtras.size() > 0) {
			for (ExtraParam param : mExtras) {

				SmartLog.getInstance().w(TAG,
						"PkIntentManager ExtraParam " + param.name + ":" + param.value);
				param.put(intent);
			}
		}

		init();

		return intent;
	}

	/**
	 * @Method Name : putExtra
	 * @author mangosteen@kiwiple.com
	 * @date : 2014. 1. 21.
	 * Desc : 일반 Extra Data
	 * @param name
	 * @param value
	 */
	public void putExtra(String name, Object value) {

		mExtras.add(new ExtraParam(name, value, false));
	}

	/**
	 * @Method Name : putExtraParcelable
	 * @author mangosteen@kiwiple.com
	 * @date : 2014. 1. 21.
	 * Desc : Parcelable Extra data 만 사용
	 * @param name
	 * @param value
	 */
	public void putExtraParcelable(String name, Object value) {

		mExtras.add(new ExtraParam(name, value, true));
	}

	/**
	 * Comment : 기본 activity finish
	 */
	public void pop(final Activity currentAct) {

		if (mClassArray.size() > 0) {

			mClassArray.remove(mClassArray.size() - 1);
		}
		log("pop");

		currentAct.finish();

	}

	public void pop(final Activity currentAct, final int anim_enter, final int anim_exit) {

		if (mClassArray.size() > 0) {

			mClassArray.remove(mClassArray.size() - 1);
		}
		log("pop");

		currentAct.finish();

	}

	/**
	 * Comment : for No Animation activity finish
	 */
	public void popForNoAnim(Activity act) {

		if (mClassArray.size() > 0) {

			mClassArray.remove(mClassArray.size() - 1);
		}
		log("pop");

		if(act != null) {
			act.finish();
		}

		// 단말기 기본 Animaion 으로 사용하기 위해 (0, 0) 주석 처리
		//		act.overridePendingTransition(0, 0);
	}


	public void popForFake(Activity act) {

		if (mClassArray.size() > 0) {

			mClassArray.remove(mClassArray.size() - 1);
		}
		log("pop");

		act.finish();
		act.overridePendingTransition(0, 0);
	}

	/**
	 * Comment : 특정 index activity finish
	 */
	public void pop(Activity act, int index) {

		if (mClassArray.size() > 0) {

			mClassArray.remove(mClassArray.size() - index);

		}
		act.finish();
	}

	/**
	 * Comment : 특정 클래스 remove()
	 */
	protected void remove(Class<?> targetCls) {

		int size = mClassArray.size();
		for (int i = 0; i < size; i++) {

			if (targetCls.getSimpleName().toString()
					.equalsIgnoreCase(mClassArray.get(i).simpleName)) {

				mClassArray.remove(i);

			}

		}

	}

	protected class ClassMap {
		protected String simpleName;
		protected Class<?> className;

		public ClassMap(String tagName, Class<?> cls) {
			simpleName = tagName;
			className = cls;
		}
	}

	public class ExtraParam {
		String name;
		Object value;
		boolean isParcelable = false;

		public ExtraParam(String n, Object v, boolean isParcelable) {
			name = n;
			value = v;
			this.isParcelable = isParcelable;
		}

		public void put(Intent i) {

			if(isParcelable == true) {

				if(value instanceof ArrayList<?>) {

					i.putExtra(name, (ArrayList<?>)(value));
				}
				else {

					i.putExtra(name, (Parcelable)(value));
				}
			}
			else {
				if (value instanceof String) {

					i.putExtra(name, (String) value);
				}
				else if (value instanceof Integer) {

					i.putExtra(name, (Integer)(value));
				}
				else if (value instanceof Long) {

					i.putExtra(name, (Long)(value));
				}
				else if (value instanceof Float) {

					i.putExtra(name, (Float)(value));
				}
				else if (value instanceof Double) {

					i.putExtra(name, (Double)(value));
				}
				else if (value instanceof Boolean) {

					i.putExtra(name, (Boolean)(value));
				}
				else if (value instanceof Character) {

					i.putExtra(name, (Character)(value));
				}
				else if(value instanceof Uri) {

					i.putExtra(name, (Uri)(value));
				}
				else if (value instanceof String[]) {

					i.putExtra(name, (String[])(value));
				}
				else if(value instanceof ArrayList<?>) {

					i.putExtra(name, (ArrayList<?>)(value));
				}
				else if(value instanceof HashMap<?, ?>) {

					i.putExtra(name, (HashMap<?, ?>)(value));
				}
			}
		}
	}

	public boolean isFirstActivity() {

		return mClassArray != null && mClassArray.size() == 0;
	}

}

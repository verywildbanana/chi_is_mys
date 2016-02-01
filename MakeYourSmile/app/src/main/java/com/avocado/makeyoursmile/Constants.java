package com.avocado.makeyoursmile;

/**
 * Created by HDlee on 15. 11. 3..
 */
public class Constants {

    public static final boolean RELEASE_BUILD = false;

    /**
     * logCat 에 log 노출 여부
     * Release build 시 false로 사용해야함 (QA 제외)
     */
    public static final boolean	IS_SHOW_SMART_LOG	= true;
    public static final boolean CRASH_REPORT_LOGGER = false;

    public static final String AVOCADO_SCHEME               = "http://schemas.avocado.com/mys";

    public static final String BROADCAST_PERMISSION			= "com.avocado.permission.BroadcastReceiver";
    //  system 종료
    public static final String ACTION_FINISH				= "com.avocado.action.finish";
    // 로그인
    public static final String ACTION_LOGIN				    = "com.avocado.action.login";
    // 로그 아웃
    public static final String ACTION_LOGOUT			    = "com.avocado.action.logout";

    public static final String ACTION_GOMAIN			    = "com.avocado.action.gomain";


    public static final String API_URL_LIVE		= "https://d.wearru.co.kr/";
    public static final String API_URL_DEV		= "https://wearru.jinuho.com/";
    public static final String API_CLIENT_ID		= "E5241BBB-3603-45A6-8CB4-F6E3D84BF97C";

    public static final String SEPARATER		= ",";
    public static final String SEPARATER2		= ",,";

    public static final int NOTI_ID_PUSH_MSG	= 99653180;	// push massage notification id


}

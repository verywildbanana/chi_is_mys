package com.avocado.makeyoursmile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreferenceManager {

    private static final String TAG = SharedPreferenceManager.class.getSimpleName();

    private final String USER_ID = "USER_ID";
    private final String GCM_REGISTER_ID = "GCM_REGISTER_ID";
    private final String SERVICE_BACKGROUND = "SERVICE_BACKGROUND";
    private final String PID = "PID";

    private static SharedPreferenceManager mInstance;

    private SharedPreferences mPreferences;


    private Editor mEditor = null;

    public SharedPreferenceManager() {

    }

    static {
        mInstance = new SharedPreferenceManager();
    }

    public static SharedPreferenceManager getInstance() {
        return mInstance;
    }

    public void init(Context ctx) {
        if (mPreferences == null) {

            mPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        }
        mEditor = mPreferences.edit();
    }

    public boolean isInit() {
        if (mPreferences != null) {
            return true;
        }

        return false;
    }


    public void setUserId(String id) {

        Editor editor = mPreferences.edit();
        editor.putString(USER_ID, id);
        editor.commit();
    }

    public String getUserId() {

        return mPreferences.getString(USER_ID, null);
    }

    public String getGCMId() {
        return mPreferences.getString(GCM_REGISTER_ID, null);
    }

    public void setGCMId(String id) {
        Editor editor = mPreferences.edit();

        if (id == null) {
            editor.remove(GCM_REGISTER_ID);
        } else {
            editor.putString(GCM_REGISTER_ID, id);
        }

        editor.commit();
    }

    public boolean isBackgroundService() {
        return mPreferences.getBoolean(SERVICE_BACKGROUND, false);
    }

    public void setBackgroundService(boolean is) {

        Editor editor = mPreferences.edit();
        editor.putBoolean(SERVICE_BACKGROUND, is);
        editor.commit();
    }
    public void setPID(int pid) {
        Editor editor = mPreferences.edit();
        editor.putInt(PID, pid);
        editor.commit();
    }

    public int getPID() {
        return mPreferences.getInt(PID, 0);
    }

    /*
    *
    *
    *
    */
}

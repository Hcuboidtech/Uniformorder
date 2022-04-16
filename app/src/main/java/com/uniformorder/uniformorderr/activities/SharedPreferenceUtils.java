package com.uniformorder.uniformorderr.activities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {

    private static final String TAG = LogUtils.makeLogTag(SharedPreferenceUtils.class);
    private static SharedPreferenceUtils sInstance;
    protected Context mContext;
    private SharedPreferences pref;
    private static final String FIRST_LAUNCH = "firstLaunch";
    private SharedPreferences.Editor editor;

    public SharedPreferenceUtils(Context context) {
        mContext = context;
        int stringId = context.getApplicationInfo().labelRes;
        pref = context.getSharedPreferences(context.getString(stringId) + "_SharedPreferences", 0);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(FIRST_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean FirstLaunch() {
        return pref.getBoolean(FIRST_LAUNCH, true);
    }

    public static synchronized SharedPreferenceUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferenceUtils(context.getApplicationContext());
        }
        return sInstance;
    }

    public void setValue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void setValue(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public void setValue(String key, double value) {
        setValue(key, Double.toString(value));
    }

    public void setValue(String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public void setValue(String key, float value) {
        editor.putFloat(key, value);
        editor.apply();
    }

    public void setValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }


    public String getStringValue(String key, String defaultValue) {
        return pref.getString(key, defaultValue);
    }

    public int getIntValue(String key, int defaultValue) {
        return pref.getInt(key, defaultValue);
    }

    public long getLongValue(String key, long defaultValue) {
        return pref.getLong(key, defaultValue);
    }

    public float getFloatValue(String key, float defaultValue) {
        return pref.getFloat(key, defaultValue);
    }

    public boolean getBoolanValue(String keyFlag, boolean defaultValue) {
        return pref.getBoolean(keyFlag, defaultValue);
    }

    public void removeKey(String key) {

        if (editor != null) {
            editor.remove(key);
            editor.apply();
        }
    }

    public void clear() {
        editor.clear().apply();
    }

    public boolean isContain(String key) {
        return pref.contains(key);
    }

}

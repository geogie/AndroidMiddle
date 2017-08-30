package com.georgeren.drawer.util;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.georgeren.drawer.InitApp;
import com.georgeren.drawer.R;

/**
 * Created by georgeRen on 2017/8/29.
 */

public class SettingUtil {
    private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(InitApp.AppContext);

    public static SettingUtil getInstance() {
        return SettingsUtilInstance.instance;
    }
    private static final class SettingsUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }

    public boolean getIsNightMode() {
        return setting.getBoolean("switch_nightMode", false);
    }
    public void setIsNightMode(boolean flag) {
        setting.edit().putBoolean("switch_nightMode", flag).apply();
    }
    public int getColor() {
        int defaultColor = InitApp.AppContext.getResources().getColor(R.color.colorPrimary);
        int color = setting.getInt("color", defaultColor);
        if ((color != 0) && Color.alpha(color) != 255) {
            return defaultColor;
        }
        return color;
    }
}

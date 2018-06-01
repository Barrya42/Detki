package com.example.derbenevsv.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class PreferenceHelper
{
//public static String SESSION_GUID_KEY = "sessionGuid";

    private static SharedPreferences instance;

//    public PreferenceHelper(Context ctx)
//    {
//        instance = ctx.getSharedPreferences("main", Context.MODE_PRIVATE);
//
//    }

    public static void Initialize(Context ctx)
    {
        instance = ctx.getSharedPreferences("main", Context.MODE_PRIVATE);
    }

    public static String GetSessionGuid()
    {
        return instance.getString("sessionGuid", "");
    }

    public static void SetSessionGuid(String newGuid)
    {
        instance.edit()
                .putString("sessionGuid", newGuid)
                .apply();
    }

    public static String GetPhone()
    {
        return instance.getString("phone", "");
    }

    public static void SetPhone(String newPhone)
    {
        instance.edit()
                .putString("phone", newPhone)
                .apply();
    }

    public void RegisterPreferenceListener(@NonNull SharedPreferences.OnSharedPreferenceChangeListener listener)
    {
        instance.registerOnSharedPreferenceChangeListener(listener);
    }

    public void UnregisterPreferenceListener(@NonNull SharedPreferences.OnSharedPreferenceChangeListener listener)
    {
        instance.unregisterOnSharedPreferenceChangeListener(listener);
    }
}

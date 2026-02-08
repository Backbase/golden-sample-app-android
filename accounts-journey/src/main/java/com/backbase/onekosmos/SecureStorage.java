package com.backbase.onekosmos;


import android.content.Context;
import android.util.Base64;

public class SecureStorage {

    private static final String PREF = "secure_store";
    private static final String DATA = "encrypted";
    private static final String IV = "iv";

    public static void save(Context context, byte[] encrypted, byte[] iv) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putString(DATA, Base64.encodeToString(encrypted, Base64.NO_WRAP))
                .putString(IV, Base64.encodeToString(iv, Base64.NO_WRAP))
                .apply();
    }

    public static byte[] getEncrypted(Context context) {
        String value = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(DATA, null);
        return value == null ? null : Base64.decode(value, Base64.NO_WRAP);
    }

    public static byte[] getIv(Context context) {
        String value = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(IV, null);
        return value == null ? null : Base64.decode(value, Base64.NO_WRAP);
    }

    public static void clear(Context context) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

}

package com.example.smartclass.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefGuru {
    private final SharedPreferences preferences;
    private static final String PREF_NAME = "prefguru";
    private static final String ID_USER = "id_user";
    private static final String ROLE = "role";
    private static final String NAME = "name";
    private static final String CREATED_AT = "created_at";

    public PrefGuru(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    public void setIdUser(int idUser) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ID_USER, idUser);
        editor.apply();
    }

    public int getIdUser() {
        return preferences.getInt(ID_USER, 0);
    }

    public void setRole(String role) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ROLE, role);
        editor.apply();
    }

    public String getRole() {
        return preferences.getString(ROLE, "0");
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ROLE, name);
        editor.apply();
    }

    public String getName() {
        return preferences.getString(NAME, "0");
    }

    public void setCreatedAt(String createdAt) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CREATED_AT, createdAt);
        editor.apply();
    }

    public String getCreatedAt() {
        return preferences.getString(CREATED_AT, "0");
    }

}





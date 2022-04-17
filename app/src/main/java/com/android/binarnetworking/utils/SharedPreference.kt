package com.android.binarnetworking.utils

import android.annotation.SuppressLint
import android.content.Context

class SharedPreference(context: Context) {

    private val pref = "authentication"
    private val sharedPreference = context.getSharedPreferences(pref, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    fun saveKey(email: String, password: String) {
        val editor = sharedPreference.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun saveKeyState(status: Boolean){
        val editor = sharedPreference.edit()
        editor.putBoolean("login_status", status)
        editor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun getPrefKey(key: String): String? {
        return sharedPreference.getString(key, "data kosong")
    }

    @SuppressLint("CommitPrefEdits")
    fun getPrefKeyStatus(key: String): Boolean{
        return sharedPreference.getBoolean(key, false)
    }

    @SuppressLint("CommitPrefEdits")
    fun clearUsername(){
        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }
}
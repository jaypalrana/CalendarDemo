package com.example.calendar.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.calendar.base.App
import com.google.gson.Gson


object SharedPref {
    val gson: Gson = Gson()
    const val SHARED_PREF_NAME = ""
    const val userLogin = "isLogin"
    const val TOKEN = "token"
    const val DEVICE_TOKEN = "deviceToken"
    const val DEVICE_ID = "deviceId"
    const val RECEIVER_ID = "receiverId"
    private var appPreference: SharedPref? = null
    var sharedPreferences: SharedPreferences =
        App.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun save(key: String, value: Any?) {

        val editor = sharedPreferences.edit()
        //  editor.clear()
        when {
            value is Boolean -> editor.putBoolean(key, (value as Boolean?)!!)
            value is Int -> editor.putInt(key, (value as Int?)!!)
            value is Float -> editor.putFloat(key, (value as Float?)!!)
            value is Long -> editor.putLong(key, (value as Long?)!!)
            value is String -> editor.putString(key, value as String?)
            value is Enum<*> -> editor.putString(key, value.toString())
            value != null -> throw RuntimeException("Attempting to save non-supported preference")
        }
        editor.apply()
        editor.commit()
    }

    fun getInstance(context: Context?): SharedPref? {
        if (appPreference == null) {
            appPreference = SharedPref
        }
        return appPreference
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String): T {
        return sharedPreferences.all[key] as T
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T
        return returnValue ?: defValue
    }

    fun saveString(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }


    fun setItemJsonArray(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getItemJsonArray(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun saveBoolean(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    //Use For Login Time
    fun setUserLoggedIn(boolean: Boolean) {
        save(userLogin, boolean)
    }

    //Check User Login
    fun isUserLogin(): Boolean {
        return get(userLogin, false)
    }

    fun setToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        val retrivedToken = sharedPreferences.getString(TOKEN, null)
        return retrivedToken
    }

    fun setDeviceToken(token: String) {
        sharedPreferences.edit().putString(DEVICE_TOKEN, token).apply()
    }

    fun getDeviceToken(): String? {
        val retrivedToken = sharedPreferences.getString(DEVICE_TOKEN, null)
        return retrivedToken
    }

    fun clearData(context: Context) {
        val myEdit = sharedPreferences.edit()
        myEdit.clear()
        myEdit.commit()
    }



}
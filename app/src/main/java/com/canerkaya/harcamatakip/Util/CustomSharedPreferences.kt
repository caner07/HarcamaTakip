package com.canerkaya.harcamatakip.Util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class CustomSharedPreferences {
    companion object {
        private val USER_NAME="user_name"
        private val GENDER = "gender"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): CustomSharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: makeCustomSharedPreferences(context).also {
                    instance = it
                }
            }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }
    fun saveUser(name:String,gender:String){
        sharedPreferences?.edit(){
            putString(USER_NAME,name)
            putString(GENDER,gender)
        }
    }
    fun getUserName():String?{
        return sharedPreferences?.getString(USER_NAME,"")
    }
    fun getUserGender():String?{
        return sharedPreferences?.getString(GENDER,"")
    }
}
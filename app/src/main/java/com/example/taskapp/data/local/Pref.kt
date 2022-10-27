package com.example.taskapp.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.taskapp.ui.profile.ProfileFragment

class Pref(private var context:Context) {

    private var pref: SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)


    fun isShowOnBoarding():Boolean{
        return pref.getBoolean(SHOW_BOARDING,false)
    }

    fun saveBoardingShow(isShowOnBoarding:Boolean){
        pref.edit().putBoolean(SHOW_BOARDING,isShowOnBoarding).apply()
    }

    fun getProfileName():String?{
        return pref.getString(PROFILE_NAME,"")
    }

    fun saveProfileName(name:String) {
        pref.edit().putString(PROFILE_NAME, name).apply()
    }

    fun getProfileAge():String?{
        return pref.getString(PROFILE_AGE,"")
    }

    fun saveProfileAge(age:String){
        pref.edit().putString(PROFILE_AGE,age).apply()
    }

    companion object{
        const val PREF_NAME = "pref.task"
        const val SHOW_BOARDING = "boarding"
        const val PROFILE_NAME = "profile_name"
        const val PROFILE_AGE = "profile_age"
    }
}
package com.example.calendar.base

import android.app.Application

class App : Application() {
    companion object {
        private lateinit var instance: App
        fun getInstance(): App = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
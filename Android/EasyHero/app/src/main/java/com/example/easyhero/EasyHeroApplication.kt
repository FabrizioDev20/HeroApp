package com.example.easyhero

import android.app.Application

class EasyHeroApplication : Application() {
    companion object {
        lateinit var instance: EasyHeroApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
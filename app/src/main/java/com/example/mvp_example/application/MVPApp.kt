package com.example.mvp_example.application

import android.app.Application
import android.content.Context

class MVPApp : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}

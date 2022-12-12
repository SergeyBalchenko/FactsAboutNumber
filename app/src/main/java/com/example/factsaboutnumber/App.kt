package com.example.factsaboutnumber

import android.app.Application
import com.example.factsaboutnumber.di.AppComponent
import com.example.factsaboutnumber.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}
package com.kariba.todoapp

import android.app.Application
import com.kariba.todoapp.di.AppComponent
import com.kariba.todoapp.di.DaggerAppComponent

class UserApplication : Application() {
    companion object{
        lateinit var appComponent : AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)

    }
}
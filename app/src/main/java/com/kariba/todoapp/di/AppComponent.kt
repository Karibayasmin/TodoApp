package com.kariba.todoapp.di

import android.content.Context
import com.kariba.todoapp.UserApplication
import com.kariba.todoapp.di.modules.MainActivityModule
import com.kariba.todoapp.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainActivityModule::class])
interface AppComponent {

    fun inject(userApplication: UserApplication)
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }
}
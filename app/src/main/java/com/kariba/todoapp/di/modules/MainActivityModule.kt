package com.kariba.todoapp.di.modules

import android.content.Context
import com.kariba.todoapp.adapter.TaskAdapter
import com.kariba.todoapp.viewmodel.TaskViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun getAdapterTask(context: Context) : TaskAdapter {

        return TaskAdapter(context)
    }

    @Provides
    fun getMainViewModel() : TaskViewModel {

        return  TaskViewModel()
    }
}
package com.kariba.todoapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kariba.todoapp.model.TaskData

class TaskViewModel : ViewModel() {
    var mutableTaskDataResponse = MutableLiveData<ArrayList<TaskData>>()
    var taskList : ArrayList<TaskData> = ArrayList()

    fun getTaskData(context: Context, task : String): LiveData<ArrayList<TaskData>>{
        mutableTaskDataResponse = MutableLiveData()
        loadTaskData(context, task)
        return mutableTaskDataResponse
    }

    private fun loadTaskData(context: Context, task: String) {
        taskList.add(TaskData(task, false))
        setTaskData(taskList)
    }

    private fun setTaskData(data: ArrayList<TaskData>) {
        mutableTaskDataResponse.value = data
    }
}
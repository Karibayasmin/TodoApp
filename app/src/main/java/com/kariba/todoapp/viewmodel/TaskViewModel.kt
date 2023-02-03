package com.kariba.todoapp.viewmodel

import android.content.Context
import android.graphics.Paint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kariba.todoapp.localdatabase.LocalDatabase
import com.kariba.todoapp.model.TaskData

class TaskViewModel : ViewModel() {


    fun getTaskData(context: Context, localDatabase: LocalDatabase, task: String = "", isUpdate: Boolean = false, position: Int = 0, taskList : ArrayList<TaskData>? = ArrayList()): LiveData<List<TaskData>> {
        loadTaskData(context, localDatabase, task, isUpdate, position, taskList)
        return localDatabase.getTaskhDao().getTaskList()
    }

    private fun loadTaskData(context: Context, localDatabase: LocalDatabase, task: String, isUpdate: Boolean, position: Int, taskList : ArrayList<TaskData>?) {
        if(isUpdate == false){
            localDatabase.getTaskhDao().insertTask(TaskData(task, false))
        }else{

            var updateTask = TaskData()
            taskList?.get(position).let {
                updateTask.id = it?.id
                updateTask.isComplete = if(taskList?.get(position)?.isComplete == true) false else true
                updateTask.taskName = it?.taskName
            }

            localDatabase.getTaskhDao().updateTask(updateTask)

        }

    }

}
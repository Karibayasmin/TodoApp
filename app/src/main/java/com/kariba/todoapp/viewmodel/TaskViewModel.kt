package com.kariba.todoapp.viewmodel

import android.content.Context
import android.graphics.Paint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kariba.todoapp.model.TaskData

class TaskViewModel : ViewModel() {
    var mutableTaskDataResponse = MutableLiveData<ArrayList<TaskData>>()
    var taskList : ArrayList<TaskData> = ArrayList()

    fun getTaskData(context: Context, task : String = "", isUpdate : Boolean = false, position : Int = 0): LiveData<ArrayList<TaskData>>{
        mutableTaskDataResponse = MutableLiveData()
        loadTaskData(context, task, isUpdate, position)
        return mutableTaskDataResponse
    }

    private fun loadTaskData(context: Context, task: String, isUpdate: Boolean, position: Int) {
        if(isUpdate == false){
            taskList.add(TaskData(task, false))
            setTaskData(taskList)
        }else{
            taskList[position].isComplete = if(taskList[position].isComplete == true) false else true
            setTaskData(taskList)
        }

    }

    private fun setTaskData(data: ArrayList<TaskData>) {
        mutableTaskDataResponse.value = data
    }

    @BindingAdapter("strikethrough")
    fun strikethrough(view: TextView, show: Boolean) {
        view.paintFlags = if (show) {
            view.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
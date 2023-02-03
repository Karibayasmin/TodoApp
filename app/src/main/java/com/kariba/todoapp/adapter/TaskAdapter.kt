package com.kariba.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kariba.todoapp.R
import com.kariba.todoapp.databinding.ItemTaskBinding
import com.kariba.todoapp.model.TaskData

class TaskAdapter(private var context : Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var taskDataList : ArrayList<TaskData> = ArrayList()

    fun setTaskData(taskDataList : ArrayList<TaskData>){
        this.taskDataList = taskDataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        var itemBinding = DataBindingUtil.inflate<ItemTaskBinding>(LayoutInflater.from(context), R.layout.item_task, parent, false)

        return TaskViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindView(context, taskDataList[position])
    }

    override fun getItemCount(): Int {
        return taskDataList.size
    }

    class TaskViewHolder(var itemBinding: ItemTaskBinding) : RecyclerView.ViewHolder(itemBinding.root){

        fun bindView(context: Context, taskData: TaskData) {
            itemBinding.taskData = taskData
            itemBinding.executePendingBindings()

        }

    }
}
package com.kariba.todoapp.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kariba.todoapp.R
import com.kariba.todoapp.databinding.ItemTaskBinding
import com.kariba.todoapp.interfaces.OnItemClickListener
import com.kariba.todoapp.model.TaskData

class TaskAdapter(private var context : Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var taskDataList : ArrayList<TaskData> = ArrayList()

    fun setTaskData(taskDataList : ArrayList<TaskData>){
        this.taskDataList = taskDataList
    }

    var onItemClickListener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        var itemBinding = DataBindingUtil.inflate<ItemTaskBinding>(LayoutInflater.from(context), R.layout.item_task, parent, false)

        return TaskViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindView(context, taskDataList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return taskDataList.size
    }

    class TaskViewHolder(var itemBinding: ItemTaskBinding) : RecyclerView.ViewHolder(itemBinding.root){

        fun bindView(
            context: Context,
            taskData: TaskData,
            onItemClickListener: OnItemClickListener?
        ) {
            itemBinding.taskData = taskData
            itemBinding.executePendingBindings()

            if (taskData.isComplete == true) {
                itemBinding.textViewTask.setPaintFlags(itemBinding.textViewTask.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

            } else {
                itemBinding.textViewTask.setPaintFlags(itemBinding.textViewTask.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())

            }

            itemBinding.checkbox.setOnClickListener {
                onItemClickListener?.onClick(it, adapterPosition)
            }

        }

    }
}
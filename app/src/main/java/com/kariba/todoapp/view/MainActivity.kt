package com.kariba.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kariba.todoapp.R
import com.kariba.todoapp.UserApplication
import com.kariba.todoapp.adapter.TaskAdapter
import com.kariba.todoapp.databinding.ActivityMainBinding
import com.kariba.todoapp.model.TaskData
import com.kariba.todoapp.viewmodel.TaskViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var taskViewModel: TaskViewModel

    @Inject
    lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        UserApplication.appComponent.inject(this)

        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        initView(binding)

    }

    private fun initView(binding: ActivityMainBinding) {

        binding.recyclerViewTask.adapter = taskAdapter
        binding.recyclerViewTask.setHasFixedSize(true)

        getTaskData()
    }

    private fun getTaskData() {
        taskViewModel.getTaskData(this, "Create Project").observe(this, object : Observer<ArrayList<TaskData>>{
            override fun onChanged(data: ArrayList<TaskData>) {
                taskAdapter.setTaskData(data)
                taskAdapter.notifyDataSetChanged()
            }

        })
    }
}
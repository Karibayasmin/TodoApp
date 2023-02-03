package com.kariba.todoapp.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.kariba.todoapp.R
import com.kariba.todoapp.UserApplication
import com.kariba.todoapp.adapter.TaskAdapter
import com.kariba.todoapp.databinding.ActivityMainBinding
import com.kariba.todoapp.interfaces.OnItemClickListener
import com.kariba.todoapp.model.TaskData
import com.kariba.todoapp.viewmodel.TaskViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnItemClickListener {

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

        taskAdapter.onItemClickListener = this

        binding.recyclerViewTask.adapter = taskAdapter
        binding.recyclerViewTask.setHasFixedSize(true)

        binding.fabAdd.setOnClickListener {
            showAddTaskAlert()
        }
    }

    private fun showAddTaskAlert() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alert_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val titleText = dialog.findViewById(R.id.titleText) as AppCompatTextView
        val editTextTask = dialog.findViewById(R.id.editText_task) as AppCompatEditText
        val buttonCross = dialog.findViewById(R.id.crossButton) as AppCompatImageView
        val buttonOk = dialog.findViewById(R.id.okButton) as MaterialButton


        buttonCross.setOnClickListener {

            dialog.dismiss()
        }

        buttonOk.setOnClickListener {
            getTaskData(this, editTextTask.text.toString())
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun getTaskData(context: Context, newTask : String) {
        taskViewModel.getTaskData(context, task = newTask).observe(this, object : Observer<ArrayList<TaskData>>{
            override fun onChanged(data: ArrayList<TaskData>) {
                taskAdapter.setTaskData(data)
                taskAdapter.notifyDataSetChanged()
            }

        })
    }

    override fun onClick(view: View, position: Int) {
        updateTask(position)
    }

    private fun updateTask(position: Int) {
        taskViewModel.getTaskData(this, isUpdate = true, position = position).observe(this, object : Observer<ArrayList<TaskData>>{
            override fun onChanged(data: ArrayList<TaskData>) {
                taskAdapter.setTaskData(data)
                taskAdapter.notifyDataSetChanged()
            }

        })
    }
}
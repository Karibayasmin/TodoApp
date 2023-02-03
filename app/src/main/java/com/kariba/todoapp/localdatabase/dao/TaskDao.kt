package com.kariba.todoapp.localdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kariba.todoapp.model.TaskData

@Dao
interface TaskDao {

    @Insert
    fun insertTask(taskData: TaskData)

    @Update
    fun updateTask(taskData: TaskData)

    @Delete
    fun deleteTask(taskData: TaskData)

    @Query("SELECT * FROM task_table")
    fun getTaskList() : LiveData<List<TaskData>>

}
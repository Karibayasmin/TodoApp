package com.kariba.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "task_table")
class TaskData (
    @SerializedName("taskName")
    var taskName : String? = "",

    @SerializedName("isComplete")
    var isComplete : Boolean? = false,

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
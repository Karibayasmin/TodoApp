package com.kariba.todoapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TaskData (
    @SerializedName("taskName")
    var taskName : String? = "",

    @SerializedName("isComplete")
    var isComplete : Boolean? = false,

) : Serializable {
    @SerializedName("id")
    var id : Int? = 0
}
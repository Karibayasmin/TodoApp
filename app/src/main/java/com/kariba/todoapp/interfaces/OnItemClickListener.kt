package com.kariba.todoapp.interfaces

import android.view.View

interface OnItemClickListener {
    fun onClick(view: View, position : Int)
}
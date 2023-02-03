package com.kariba.todoapp.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kariba.todoapp.localdatabase.dao.TaskDao
import com.kariba.todoapp.model.TaskData

@Database(entities = [TaskData::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun getTaskhDao() : TaskDao

    companion object{
        @Volatile
        private var INSTANCE : LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "taskDB")
                        .allowMainThreadQueries()
                        .enableMultiInstanceInvalidation()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

    fun clearAllData() {
        INSTANCE?.clearAllTables()
    }
}
package com.example.taskapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.data.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase:RoomDatabase() {
    abstract fun dao(): TaskDao
}
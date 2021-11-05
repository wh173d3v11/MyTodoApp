package com.example.mytodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytodoapp.data.models.ToDoTask

@Database(entities = [ToDoTask::class],version = 1,exportSchema = false)
abstract class ToDoDatabase:RoomDatabase() {

    abstract fun todoDao():ToDoDao
}
package com.example.myapplication.data

import com.example.myapplication.model.TaskDetail
import android.content.Context

object DataManager {
    private lateinit var dataSource: TaskDataSource

    fun init(context: Context) {
        dataSource = InMemoryTaskDataSource(context)
    }

    fun getAllTasks(): List<String> = dataSource.getAllTasks()
    fun searchTasks(query: String): List<String> = dataSource.searchTasks(query)
    fun getTaskDetail(name: String): TaskDetail = dataSource.getTaskDetail(name)
    fun addFavorite(name: String): Boolean = dataSource.addFavorite(name)
    fun removeFavorite(name: String): Boolean = dataSource.removeFavorite(name)
    fun getFavorites(): List<String> = dataSource.getFavorites()
}


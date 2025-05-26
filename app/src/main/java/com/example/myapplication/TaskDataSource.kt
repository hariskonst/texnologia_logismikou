package com.example.myapplication.data

import com.example.myapplication.model.TaskDetail

interface TaskDataSource {
    fun getAllTasks(): List<String>
    fun searchTasks(query: String): List<String>
    fun getTaskDetail(name: String): TaskDetail
    fun addFavorite(name: String): Boolean
    fun getFavorites(): List<String>
    fun removeFavorite(name: String): Boolean
}

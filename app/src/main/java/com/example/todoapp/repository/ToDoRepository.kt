package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.ToDo
import com.example.todoapp.data.ToDoDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao
) {

    val allToDos: LiveData<List<ToDo>> = toDoDao.getAllToDos()

    suspend fun insertToDo(todo: ToDo) {
        toDoDao.insertToDo(todo)
    }

    suspend fun updateToDo(todo: ToDo) {
        toDoDao.updateToDo(todo)
    }

    suspend fun deleteToDo(todo: ToDo) {
        toDoDao.deleteToDo(todo)
    }

    suspend fun getToDoById(id: Int): ToDo? {
        return toDoDao.getToDoById(id)
    }
}

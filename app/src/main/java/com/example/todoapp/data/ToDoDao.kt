package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todo: ToDo)

    @Update
    suspend fun updateToDo(todo: ToDo)

    @Delete
    suspend fun deleteToDo(todo: ToDo)

    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllToDos(): LiveData<List<ToDo>>

    @Query("SELECT * FROM todo_table WHERE id = :todoId")
    suspend fun getToDoById(todoId: Int): ToDo?
}

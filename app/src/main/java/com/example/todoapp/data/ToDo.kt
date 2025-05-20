package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable // Serializable importu

@Entity(tableName = "todo_table")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean = false
) : Serializable // Parcelable yerine bu satır!

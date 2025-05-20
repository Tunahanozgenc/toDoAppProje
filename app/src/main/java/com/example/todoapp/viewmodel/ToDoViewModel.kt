package com.example.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.ToDo
import com.example.todoapp.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    // Tüm verileri gözlemlemek için
    val allToDos: LiveData<List<ToDo>> = repository.allToDos

    // Yeni veri ekleme
    fun addToDo(todo: ToDo) {
        viewModelScope.launch {
            repository.insertToDo(todo)
        }
    }

    // Veri silme
    fun deleteToDo(todo: ToDo) {
        viewModelScope.launch {
            repository.deleteToDo(todo)
        }
    }

    // ID'ye göre tek bir veriyi çekme
    fun getToDoById(id: Int): LiveData<ToDo?> {
        val result = MutableLiveData<ToDo?>()
        viewModelScope.launch {
            result.postValue(repository.getToDoById(id))
        }
        return result
    }
}

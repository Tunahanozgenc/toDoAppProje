package com.example.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.ToDo
import com.example.todoapp.databinding.ItemTodoBinding

class ToDoAdapter(
    private val onItemClick: (ToDo) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private val todoList = mutableListOf<ToDo>()

    fun submitList(newList: List<ToDo>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ToDoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: ToDo) {
            binding.tvTitle.text = todo.title
            binding.tvDescription.text = todo.description

            // Tıklanınca onItemClick fonksiyonunu çağır
            binding.root.setOnClickListener {
                onItemClick(todo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int = todoList.size
}

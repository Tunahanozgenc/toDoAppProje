package com.example.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentToDoListScreenBinding
import com.example.todoapp.ui.adapter.ToDoAdapter
import com.example.todoapp.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListScreenFragment : Fragment() {

    private var _binding: FragmentToDoListScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ToDoViewModel by viewModels()
    private lateinit var adapter: ToDoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoListScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter kurulumu: item tıklanınca detay ekranına gider
        adapter = ToDoAdapter { selectedToDo ->
            val action = ToDoListScreenFragmentDirections
                .actionToDoListScreenFragmentToToDoDetailScreenFragment(selectedToDo)
            findNavController().navigate(action)
        }

        // RecyclerView ayarları
        binding.rvToDoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvToDoList.adapter = adapter

        // Veriler gözlemleniyor
        viewModel.allToDos.observe(viewLifecycleOwner) { todos ->
            adapter.submitList(todos)
        }

        // Ekle butonuna tıklanınca kayıt ekranına gider
        binding.btnAdd.setOnClickListener {
            val action = ToDoListScreenFragmentDirections
                .actionToDoListScreenFragmentToToDoAddScreenFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

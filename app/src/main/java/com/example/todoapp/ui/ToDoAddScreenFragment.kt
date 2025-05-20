package com.example.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.ToDo
import com.example.todoapp.databinding.FragmentToDoAddScreenBinding
import com.example.todoapp.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoAddScreenFragment : Fragment() {

    private var _binding: FragmentToDoAddScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoAddScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val todo = ToDo(
                    id = 0, // Auto-increment
                    title = title,
                    description = description
                )
                viewModel.addToDo(todo)
                Toast.makeText(requireContext(), "Görev kaydedildi", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack() // Geri dön (anasayfa)
            } else {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

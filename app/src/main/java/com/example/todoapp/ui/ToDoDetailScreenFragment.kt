package com.example.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.databinding.FragmentToDoDetailScreenBinding
import com.example.todoapp.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoDetailScreenFragment : Fragment() {

    private var _binding: FragmentToDoDetailScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ToDoViewModel by viewModels()
    private val args: ToDoDetailScreenFragmentArgs by navArgs() // navGraph'tan gelen veriyi alır

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val todo = args.todo

        // Verileri ekranda göster
        binding.editTextTitle.setText(todo.title)
        binding.editTextDescription.setText(todo.description)

        // Güncelleme
        binding.buttonUpdate.setOnClickListener {
            val newTitle = binding.editTextTitle.text.toString()
            val newDesc = binding.editTextDescription.text.toString()

            if (newTitle.isNotEmpty() && newDesc.isNotEmpty()) {
                val updatedToDo = todo.copy(title = newTitle, description = newDesc)
                viewModel.addToDo(updatedToDo)
                Toast.makeText(requireContext(), "Güncellendi", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Alanlar boş olamaz!", Toast.LENGTH_SHORT).show()
            }
        }

        // Silme
        binding.buttonDelete.setOnClickListener {
            viewModel.deleteToDo(todo)
            Toast.makeText(requireContext(), "Silindi", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

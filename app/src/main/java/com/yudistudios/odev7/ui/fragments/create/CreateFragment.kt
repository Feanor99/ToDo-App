package com.yudistudios.odev7.ui.fragments.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.databinding.FragmentCreateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFragment : Fragment() {

    private val viewModel: CreateViewModel by viewModels()
    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)

        binding.toolbarTitle = "Yapılacak İş Kayıt"
        binding.viewModel = viewModel
        observers()

        return binding.root
    }

    private fun observers() {

        viewModel.createButtonIsClicked.observe(viewLifecycleOwner) {
            if (it) {
                val text = binding.editTextDescription.text.toString()
                if (text.isEmpty()) {
                    binding.textInputLayout.error = "Lütfen boş bırakmayınız"
                } else {
                    val item = ToDoListItem(0, text)
                    viewModel.createNewToDoListItem(item)
                    Snackbar.make(binding.root, "Kaydedildi", Snackbar.LENGTH_LONG).show()
                }
                viewModel.createButtonIsClicked.value = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
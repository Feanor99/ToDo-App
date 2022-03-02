package com.yudistudios.odev7.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.toolbarTitle = "Yapılacak İş Detay"

        val args: DetailFragmentArgs by navArgs()
        viewModel.toDoListItem.value = args.toDoListItem
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observers()

        return binding.root
    }

    private fun observers() {

        viewModel.updateButtonIsClicked.observe(viewLifecycleOwner) {
            if (it) {
                val text = binding.editTextDescription.text.toString()
                if (text.isEmpty()) {
                    binding.textInputLayout.error = "Lütfen boş bırakmayınız"
                } else {
                    val item = viewModel.toDoListItem.value
                    if (item != null) {
                        val updatedItem = ToDoListItem(item.id, text)
                        viewModel.toDoListItem.value = updatedItem
                        viewModel.updateToDoListItem()
                        Snackbar.make(binding.root, "Güncellendi", Snackbar.LENGTH_LONG).show()
                    }
                }
                viewModel.updateButtonIsClicked.value = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
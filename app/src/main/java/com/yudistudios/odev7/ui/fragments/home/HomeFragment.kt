package com.yudistudios.odev7.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yudistudios.odev7.R
import com.yudistudios.odev7.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import android.app.SearchManager
import android.content.Context

import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.toolbarTitle = "Yapılacaklar"

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val toDoListRecyclerItemClickListeners = ToDoListRecyclerItemClickListeners(
            { item ->
                viewModel.deleteToDoItem(item)
            },
            { item ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
                findNavController().navigate(action)
            }
        )

        val adapter = ToDoListRecyclerAdapter(requireContext(), toDoListRecyclerItemClickListeners)
        binding.adapter = adapter

        observers()
        //  viewModel.generateTestData()

        return binding.root
    }

    private fun observers() {

        viewModel.createButtonIsClicked.observe(viewLifecycleOwner) {
            if (it) {
                val action = HomeFragmentDirections.actionHomeFragmentToCreateFragment()
                findNavController().navigate(action)
                viewModel.createButtonIsClicked.value = false
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_fragment_toolbar, menu)
        val searchItem = menu.findItem(R.id.item_search)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    lifecycleScope.launch {
                        val items = viewModel.searchToDoItem(newText)
                        _binding?.adapter?.submitList(items ?: listOf())
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    //viewModel.searchToDoItem(query)
                    return true
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_search ->
                return false
            else -> {}
        }
        searchView!!.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }


}
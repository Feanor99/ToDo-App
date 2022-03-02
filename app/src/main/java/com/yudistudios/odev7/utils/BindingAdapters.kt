package com.yudistudios.odev7.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.ui.fragments.home.ToDoListRecyclerAdapter

@BindingAdapter("toDoListRecyclerAdapter")
fun RecyclerView.toDoListRecyclerAdapter(list: List<ToDoListItem>?) {
    val adapter = adapter as ToDoListRecyclerAdapter
    adapter.submitList(list ?: listOf<ToDoListItem>())
}
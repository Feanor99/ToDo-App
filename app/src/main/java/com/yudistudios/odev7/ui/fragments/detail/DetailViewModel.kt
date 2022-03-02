package com.yudistudios.odev7.ui.fragments.detail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.yudistudios.odev7.database.dao.ToDoListDao
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.repositories.ToDoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(toDoListDao: ToDoListDao) : ViewModel() {
    private val repository = ToDoListRepository(toDoListDao)

    val toDoListItem = MutableLiveData<ToDoListItem>()

    val updateButtonIsClicked = MutableLiveData(false)

    fun updateButtonOnClick() {
        updateButtonIsClicked.value = true
    }

    fun updateToDoListItem() {
        val item = toDoListItem.value

        if (item != null) {
            repository.updateToDoItem(item)
        }
    }
}
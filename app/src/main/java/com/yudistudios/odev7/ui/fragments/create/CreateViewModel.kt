package com.yudistudios.odev7.ui.fragments.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yudistudios.odev7.database.dao.ToDoListDao
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.repositories.ToDoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(toDoListDao: ToDoListDao) : ViewModel() {
    private val repository = ToDoListRepository(toDoListDao)

    val createButtonIsClicked = MutableLiveData(false)

    fun createButtonOnClick() {
        createButtonIsClicked.value = true
    }

    fun createNewToDoListItem(item: ToDoListItem) {
        repository.createToDoItem(item)
    }
}
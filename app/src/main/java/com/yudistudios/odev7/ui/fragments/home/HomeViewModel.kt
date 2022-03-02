package com.yudistudios.odev7.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yudistudios.odev7.database.dao.ToDoListDao
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.repositories.ToDoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(toDoListDao: ToDoListDao) : ViewModel() {
    private val repository = ToDoListRepository(toDoListDao)

    private var items: LiveData<List<ToDoListItem>?> = repository.getItems().asLiveData()
    val recyclerItems get() = items

    val createButtonIsClicked = MutableLiveData(false)

    fun createButtonOnClick() {
        createButtonIsClicked.value = true
    }

    fun deleteToDoItem(item: ToDoListItem) {
        repository.deleteToDoItem(item)
    }

    suspend fun searchToDoItem(str: String): List<ToDoListItem>? {
        return repository.searchItems(str).firstOrNull()
    }

    fun generateTestData() {
        repository.generateTestData()
    }
}
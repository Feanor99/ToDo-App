package com.yudistudios.odev7.repositories

import com.yudistudios.odev7.database.dao.ToDoListDao
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.database.setup.TestData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoListRepository(private val toDoListDao: ToDoListDao) {

    fun generateTestData() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                TestData().generateTestData()?.let { toDoListDao.insertAll(*it.toTypedArray()) }
            }
        }
    }

    fun createToDoItem(item: ToDoListItem) {
        CoroutineScope(Dispatchers.IO).launch {
            toDoListDao.insertToDoListItem(item)
        }
    }

    fun updateToDoItem(item: ToDoListItem) {
        CoroutineScope(Dispatchers.IO).launch {
            toDoListDao.updateToDoListItem(item)
        }
    }

    fun deleteToDoItem(item: ToDoListItem) {
        CoroutineScope(Dispatchers.IO).launch {
            toDoListDao.deleteToDoListItem(item)
        }
    }

    fun getItems(): Flow<List<ToDoListItem>?> {
        return toDoListDao.getToDoList()
    }

    fun searchItems(string: String): Flow<List<ToDoListItem>?> {
        return toDoListDao.searchToDoListItemByDescription(string)
    }
}
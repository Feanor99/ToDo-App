package com.yudistudios.odev7.database.dao

import androidx.room.*
import com.yudistudios.odev7.database.entities.ToDoListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDao {
    @Insert
    suspend fun insertToDoListItem(item: ToDoListItem)

    @Update
    suspend fun updateToDoListItem(item: ToDoListItem)

    @Delete
    suspend fun deleteToDoListItem(item: ToDoListItem)

    @Insert
    suspend fun insertAll(vararg item: ToDoListItem)

    @Query("SELECT * FROM to_do_list WHERE description LIKE '%' || :description || '%'")
    fun searchToDoListItemByDescription(description: String): Flow<List<ToDoListItem>?>

    @Query("SELECT * FROM to_do_list")
    fun getToDoList(): Flow<List<ToDoListItem>?>
}
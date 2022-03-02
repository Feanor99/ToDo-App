package com.yudistudios.odev7.database.setup

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yudistudios.odev7.database.dao.ToDoListDao
import com.yudistudios.odev7.database.entities.ToDoListItem

@Database(entities = [ToDoListItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun toDoListDao(): ToDoListDao
}
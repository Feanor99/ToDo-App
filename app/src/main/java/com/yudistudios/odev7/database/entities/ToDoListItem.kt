package com.yudistudios.odev7.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "to_do_list")
@Parcelize
data class ToDoListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String
) : Parcelable
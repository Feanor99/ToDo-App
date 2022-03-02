package com.yudistudios.odev7.database.setup

import android.content.Context
import androidx.room.Room
import com.yudistudios.odev7.database.dao.ToDoListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModules() {
    @Provides
    @Singleton
    @Synchronized
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app_database"
        )
            .createFromAsset("app_database.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideToDoListDao(appDatabase: AppDatabase): ToDoListDao {
        return appDatabase.toDoListDao()
    }
}
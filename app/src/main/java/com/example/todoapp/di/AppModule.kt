package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.ToDoDao
import com.example.todoapp.data.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(
        @ApplicationContext app: Context
    ): ToDoDatabase {
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            "todo_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideToDoDao(db: ToDoDatabase): ToDoDao {
        return db.toDoDao()
    }
}

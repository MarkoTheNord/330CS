package com.marko.a330_cs.config

import android.content.Context
import androidx.room.Room
import com.marko.a330_cs.dao.UserDao
import com.marko.a330_cs.repo.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "user.db"
        ).build()
    }

    @Provides
    fun provideUserDao(db: UserDatabase): UserDao {
        return db.userDao()
    }
}
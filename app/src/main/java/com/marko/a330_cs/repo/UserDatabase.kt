package com.marko.a330_cs.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marko.a330_cs.dao.UserDao
import com.marko.a330_cs.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
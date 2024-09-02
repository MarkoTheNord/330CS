package com.marko.a330_cs.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.marko.a330_cs.entity.User

@Dao
interface UserDao {
    @Upsert
    suspend fun insert(user: User)

    @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUser(email: String, password: String): User?
}
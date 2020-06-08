package com.utek.android.utekapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserInfoDao {
    @Insert
    fun insert(userInfo: UserInfo)

    @Update
    fun update(userInfo: UserInfo)

    @Query("SELECT * FROM user_info_table ORDER BY id DESC LIMIT 1")
    fun getUser(): UserInfo?

    @Query("DELETE from user_info_table")
    fun deleteAll()
}
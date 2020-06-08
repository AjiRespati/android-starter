package com.utek.android.utekapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info_table")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "user_sessionId")
    var sessionId: String = "init sessionId",

    @ColumnInfo(name = "user_deviceId")
    var deviceId: String = "init deviceId"

)
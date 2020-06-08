package com.utek.android.utekapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserInfo::class], version = 1, exportSchema = false)
abstract class UtekAppDatabase : RoomDatabase() {
    abstract val userInfoDao: UserInfoDao

    companion object {
        @Volatile
        private var INSTANCE: UtekAppDatabase? = null

        fun getInstance(context: Context): UtekAppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UtekAppDatabase::class.java,
                        "utek_app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

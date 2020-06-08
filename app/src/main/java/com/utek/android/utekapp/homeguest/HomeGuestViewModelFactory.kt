package com.utek.android.utekapp.homeguest

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utek.android.utekapp.database.UserInfoDao

class HomeGuestViewModelFactory(
    private val userDataSource: UserInfoDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeGuestViewModel::class.java)) {
            return HomeGuestViewModel(userDataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
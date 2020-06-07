package com.utek.android.utekapp.screen.memberdetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utek.android.utekapp.network.AppMember

class AppMemberDetailViewModelFactory(private val appMember: AppMember, private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppMemberDetailViewModel::class.java)) {
            return AppMemberDetailViewModel(appMember, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.utek.android.utekapp.screen.memberdetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utek.android.utekapp.network.AppMember

class AppMemberDetailViewModel(appMember: AppMember, app: Application) : ViewModel() {

    private val _selectedAppMember = MutableLiveData<AppMember>()
    val selectedAppMember: LiveData<AppMember>
        get() = _selectedAppMember

    init {
        _selectedAppMember.value = appMember
    }

}

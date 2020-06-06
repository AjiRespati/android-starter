package com.utek.android.utekapp.homeguest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utek.android.utekapp.network.AppMember
import com.utek.android.utekapp.network.AppListRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class AppListStatus { LOADING, ERROR, DONE }

class HomeGuestViewModel : ViewModel() {

    private val _status = MutableLiveData<AppListStatus>()
    val status: LiveData<AppListStatus>
        get() = _status

    private val _appMembers = MutableLiveData<List<AppMember>>()
    val appMembers: LiveData<List<AppMember>>
        get() = _appMembers

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getAppListRequest()
    }

    private fun getAppListRequest() {
        coroutineScope.launch {
            val getListRequestDeffered = AppListRequest.RETROFIT_SERVICE_GET.getAppListAsync()
            try {
                _status.value = AppListStatus.LOADING
                val listResult = getListRequestDeffered.await()
                _status.value = AppListStatus.DONE
                _appMembers.value = listResult
            } catch (t: Exception) {
                _status.value = AppListStatus.ERROR
                _appMembers.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
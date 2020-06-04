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

class HomeGuestViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
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
                val listResult = getListRequestDeffered.await()
                _status.value = "Success retrieved  ${listResult.size} data"
                if (listResult.size > 1) {
                    _appMembers.value = listResult
                }
            } catch (t: Exception) {
                _status.value = "Failure: " + t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
package com.utek.android.utekapp.homeguest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utek.android.utekapp.network.AppListRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeGuestViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

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
                _response.value = listResult.toString()
            } catch (t: Throwable) {
                _response.value = "Failure: " + t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
package com.utek.android.utekapp.homeguest

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utek.android.utekapp.database.UserInfo
import com.utek.android.utekapp.database.UserInfoDao
import com.utek.android.utekapp.network.AppListRequest
import com.utek.android.utekapp.network.AppMember
import kotlinx.coroutines.*

enum class AppListStatus { LOADING, ERROR, DONE }

class HomeGuestViewModel(val database: UserInfoDao, application: Application) : ViewModel() {

    private val _status = MutableLiveData<AppListStatus>()
    val status: LiveData<AppListStatus>
        get() = _status

    private val _appMembers = MutableLiveData<List<AppMember>>()
    val appMembers: LiveData<List<AppMember>>
        get() = _appMembers

    private val _navigateToSelectedAppMember = MutableLiveData<AppMember>()
    val navigateToSelectedAppMember: LiveData<AppMember>
        get() = _navigateToSelectedAppMember

    private val _navigateToSignIn = MutableLiveData<Boolean>()
    val navigateToSignIn: LiveData<Boolean>
        get() = _navigateToSignIn

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _navigateToSignIn.value = false
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

    private var userInfo: UserInfo? = null

    fun displayAppMemberDetails(appMember: AppMember) {
        // find sessionId in database
        // if found, make sessionInfo request to backend
        // response success, insert sessionInfo to database, navigate to selected member
        // response fail, navigate to signIn or signUp page

        Log.i("TEST",appMember.imgSrcUrl)
        coroutineScope.launch {
            Log.i("TEST","DIPANGGILKAH...?")
            userInfo = getUserInfoFromDatabase()
            if (userInfo == null) {
                _navigateToSignIn.value = true
                Log.i("TEST","userInfo null, $userInfo ")
                // sementara biar muncul kegiatan
                _navigateToSelectedAppMember.value = appMember
            }
            else {
                Log.i("TEST","DIPANGGIL KESINIKAH...? ${userInfo!!.id}")
                _navigateToSelectedAppMember.value = appMember
            }
        }
    }

    private suspend fun getUserInfoFromDatabase(): UserInfo? {
        return withContext(Dispatchers.IO) {
            var user = database.getUser()
            if (user != null) {
                Log.i("TEST","Dapet User , ${user.id} ")
            }
            Log.i("TEST","Wah gak dapet User")
            if (user?.sessionId == "init sessionId") {
                user = null
            }
            user
        }
    }

    fun displayAppMemberDetailsComplete() {
        _navigateToSelectedAppMember.value = null
    }

    fun navigateToSignInComplete(){
        _navigateToSignIn.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
package com.utek.android.utekapp.screen.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    private val _userSignInInput = MutableLiveData<String>()
    val userSignInInput: LiveData<String>
        get() = _userSignInInput

    private val _userSignInInputError = MutableLiveData<Boolean>()
    val userSignInInputError: LiveData<Boolean>
        get() = _userSignInInputError




}

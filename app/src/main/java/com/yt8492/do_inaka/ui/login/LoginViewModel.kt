package com.yt8492.do_inaka.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yt8492.do_inaka.domain.model.Password
import com.yt8492.do_inaka.domain.model.Username
import com.yt8492.do_inaka.usecase.checklogin.CheckLoginResult
import com.yt8492.do_inaka.usecase.checklogin.CheckLoginUseCase
import com.yt8492.do_inaka.usecase.login.LoginResult
import com.yt8492.do_inaka.usecase.login.LoginUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val checkLoginUseCase: CheckLoginUseCase
) : ViewModel() {

    private val _isLoggedIn =  MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<LoginResult.Failure>()
    val error: LiveData<LoginResult.Failure>
        get() = _error

    fun checkLogin(): Job = viewModelScope.launch {
        _loading.value = true
        val result = checkLoginUseCase.execute()
        when (result) {
            is CheckLoginResult.LoggedIn -> {
                _isLoggedIn.value = true
            }
            CheckLoginResult.NotLoggedIn -> {
                _isLoggedIn.value = false
            }
        }
        _loading.value = false
    }

    fun login(username: String, password: String): Job = viewModelScope.launch {
        _loading.value = true
        val result = loginUseCase.execute(Username(username), Password(password))
        when (result) {
            is LoginResult.Success -> {
                _isLoggedIn.value = true
            }
            is LoginResult.Failure -> {
                _error.value = result
                _isLoggedIn.value = false
            }
        }
        _loading.value = false
    }
}
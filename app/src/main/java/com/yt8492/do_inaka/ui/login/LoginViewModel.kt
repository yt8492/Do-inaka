package com.yt8492.do_inaka.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yt8492.do_inaka.domain.model.LoginUser
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

    private val _loginUser = MutableLiveData<LoginUser>()
    val loginUser: LiveData<LoginUser>
        get() = _loginUser

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<LoginResult.Failure>()
    val error: LiveData<LoginResult.Failure>
        get() = _error

    fun checkLogin(): Job = viewModelScope.launch {
        _loading.value = true
        when (val result = checkLoginUseCase.execute()) {
            is CheckLoginResult.LoggedIn -> {
                _loginUser.value = result.loginUser
            }
        }
        _loading.value = false
    }

    fun login(username: String, password: String): Job = viewModelScope.launch {
        _loading.value = true
        when (val result = loginUseCase.execute(Username(username), Password(password))) {
            is LoginResult.Success -> {
                _loginUser.value = result.loginUser
            }
            is LoginResult.Failure -> {
                _error.value = result
            }
        }
        _loading.value = false
    }
}
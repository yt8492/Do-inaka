package com.yt8492.do_inaka.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yt8492.do_inaka.usecase.checklogin.CheckLoginUseCase
import com.yt8492.do_inaka.usecase.login.LoginUseCase
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkLoginUseCase: CheckLoginUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == LoginViewModel::class.java) {
            "Unknown ViewModel class"
        }
        return LoginViewModel(loginUseCase, checkLoginUseCase) as T
    }
}
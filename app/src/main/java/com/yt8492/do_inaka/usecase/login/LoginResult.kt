package com.yt8492.do_inaka.usecase.login

import com.yt8492.do_inaka.domain.model.LoginUser

sealed class LoginResult {
    data class Success(val loginUser: LoginUser) : LoginResult()
    sealed class Failure : LoginResult() {
        object EmptyUsername : Failure()
        object EmptyPassword : Failure()
        object UserNotFound : Failure()
        object InvalidPassword : Failure()
        object UnknownError : Failure()
    }
}
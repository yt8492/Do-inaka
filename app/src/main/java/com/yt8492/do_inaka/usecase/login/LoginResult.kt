package com.yt8492.do_inaka.usecase.login

import com.yt8492.do_inaka.domain.model.UserToken

sealed class LoginResult {
    data class Success(val token: UserToken) : LoginResult()
    sealed class Failure : LoginResult() {
        object EmptyUsername : Failure()
        object EmptyPassword : Failure()
        object InvalidPassword : Failure()
    }
}
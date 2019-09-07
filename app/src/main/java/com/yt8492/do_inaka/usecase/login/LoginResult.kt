package com.yt8492.do_inaka.usecase.login

import com.yt8492.do_inaka.domain.model.UserToken

sealed class LoginResult {
    data class Success(val token: UserToken) : LoginResult()
    object Failure : LoginResult()
}
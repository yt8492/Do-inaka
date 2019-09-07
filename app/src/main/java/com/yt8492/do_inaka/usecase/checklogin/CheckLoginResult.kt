package com.yt8492.do_inaka.usecase.checklogin

import com.yt8492.do_inaka.domain.model.LoginUser

sealed class CheckLoginResult {
    data class LoggedIn(val loginUser: LoginUser) : CheckLoginResult()
    object NotLoggedIn : CheckLoginResult()
}
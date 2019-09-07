package com.yt8492.do_inaka.usecase.checklogin

import com.yt8492.do_inaka.domain.model.UserToken

sealed class CheckLoginResult {
    data class LoggedIn(val token: UserToken) : CheckLoginResult()
    object NotLoggedIn : CheckLoginResult()
}
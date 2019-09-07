package com.yt8492.do_inaka.domain.model

data class LoginUser(
    val userToken: UserToken,
    val userType: UserType
)
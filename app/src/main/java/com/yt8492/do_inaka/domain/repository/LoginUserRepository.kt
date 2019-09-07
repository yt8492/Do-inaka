package com.yt8492.do_inaka.domain.repository

import com.yt8492.do_inaka.domain.model.LoginUser

interface LoginUserRepository {
    suspend fun getLoginUser(): LoginUser?
    suspend fun saveLoginUser(user: LoginUser)
}
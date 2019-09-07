package com.yt8492.do_inaka.domain.repository

import com.yt8492.do_inaka.domain.model.Password
import com.yt8492.do_inaka.domain.model.UserToken
import com.yt8492.do_inaka.domain.model.Username

interface LoginRepository {
    suspend fun login(username: Username, password: Password): UserToken
}
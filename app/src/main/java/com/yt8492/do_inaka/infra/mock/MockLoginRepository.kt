package com.yt8492.do_inaka.infra.mock

import com.yt8492.do_inaka.domain.model.*
import com.yt8492.do_inaka.domain.repository.LoginRepository

object MockLoginRepository : LoginRepository {
    override suspend fun login(username: Username, password: Password): LoginUser {
        return LoginUser(
            UserToken(username.value + password.value),
            when (username.value.length % 2) {
                0 -> UserType.Requester
                1 -> UserType.Driver
                else -> error("Illegal State")
            }
        )
    }
}
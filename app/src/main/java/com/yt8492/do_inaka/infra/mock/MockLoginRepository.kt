package com.yt8492.do_inaka.infra.mock

import com.yt8492.do_inaka.domain.model.Password
import com.yt8492.do_inaka.domain.model.UserToken
import com.yt8492.do_inaka.domain.model.Username
import com.yt8492.do_inaka.domain.repository.LoginRepository

object MockLoginRepository : LoginRepository {
    override suspend fun login(username: Username, password: Password): UserToken {
        return UserToken(username.value + password.value)
    }
}
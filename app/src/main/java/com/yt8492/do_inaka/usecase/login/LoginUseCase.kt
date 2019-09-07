package com.yt8492.do_inaka.usecase.login

import com.yt8492.do_inaka.domain.model.Password
import com.yt8492.do_inaka.domain.model.Username

interface LoginUseCase {
    suspend fun execute(username: Username, password: Password): LoginResult
}
package com.yt8492.do_inaka.usecase

import com.yt8492.do_inaka.domain.model.Password
import com.yt8492.do_inaka.domain.model.Username

interface LoginUseCase {
    fun execute(username: Username, password: Password)
}
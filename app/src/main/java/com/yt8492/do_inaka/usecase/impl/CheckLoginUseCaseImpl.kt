package com.yt8492.do_inaka.usecase.impl

import com.yt8492.do_inaka.domain.repository.LoginUserRepository
import com.yt8492.do_inaka.usecase.checklogin.CheckLoginResult
import com.yt8492.do_inaka.usecase.checklogin.CheckLoginUseCase
import javax.inject.Inject

class CheckLoginUseCaseImpl @Inject constructor(
    private val loginUserRepository: LoginUserRepository
) : CheckLoginUseCase {

    override suspend fun execute(): CheckLoginResult {
        val token = loginUserRepository.getLoginUser()
        return if (token != null) {
            CheckLoginResult.LoggedIn(token)
        } else {
            CheckLoginResult.NotLoggedIn
        }
    }
}
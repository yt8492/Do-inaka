package com.yt8492.do_inaka.usecase.impl

import com.yt8492.do_inaka.domain.repository.AuthTokenRepository
import com.yt8492.do_inaka.usecase.checklogin.CheckLoginResult
import com.yt8492.do_inaka.usecase.checklogin.CheckLoginUseCase
import javax.inject.Inject

class CheckLoginUseCaseImpl @Inject constructor(
    private val authTokenRepository: AuthTokenRepository
) : CheckLoginUseCase {

    override suspend fun execute(): CheckLoginResult {
        val token = authTokenRepository.getToken()
        return if (token != null) {
            CheckLoginResult.LoggedIn(token)
        } else {
            CheckLoginResult.NotLoggedIn
        }
    }
}
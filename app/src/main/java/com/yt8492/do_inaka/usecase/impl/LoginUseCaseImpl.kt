package com.yt8492.do_inaka.usecase.impl

import com.yt8492.do_inaka.domain.model.Password
import com.yt8492.do_inaka.domain.model.Username
import com.yt8492.do_inaka.domain.repository.AuthTokenRepository
import com.yt8492.do_inaka.domain.repository.LoginRepository
import com.yt8492.do_inaka.usecase.login.LoginResult
import com.yt8492.do_inaka.usecase.login.LoginUseCase
import java.lang.Exception
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authTokenRepository: AuthTokenRepository
) : LoginUseCase {

    override suspend fun execute(username: Username, password: Password): LoginResult =
        try {
            val token = loginRepository.login(username, password)
            authTokenRepository.saveToken(token)
            LoginResult.Success(token)
        } catch (e: Exception) {
            LoginResult.Failure
        }
}
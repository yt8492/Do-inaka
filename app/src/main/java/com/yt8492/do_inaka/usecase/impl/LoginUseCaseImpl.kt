package com.yt8492.do_inaka.usecase.impl

import com.yt8492.do_inaka.domain.model.Password
import com.yt8492.do_inaka.domain.model.Username
import com.yt8492.do_inaka.domain.repository.LoginUserRepository
import com.yt8492.do_inaka.domain.repository.LoginRepository
import com.yt8492.do_inaka.usecase.login.LoginResult
import com.yt8492.do_inaka.usecase.login.LoginUseCase
import io.grpc.Status
import io.grpc.StatusRuntimeException
import java.lang.Exception
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val loginUserRepository: LoginUserRepository
) : LoginUseCase {

    override suspend fun execute(username: Username, password: Password): LoginResult {
        if (username.value.isBlank()) {
            return LoginResult.Failure.EmptyUsername
        }
        if (username.value.isBlank()) {
            return LoginResult.Failure.EmptyPassword
        }
        return try {
            val loginUser = loginRepository.login(username, password)
            loginUserRepository.saveLoginUser(loginUser)
            LoginResult.Success(loginUser)
        } catch (e: StatusRuntimeException) {
            if (e.status.code == Status.UNAUTHENTICATED.code) {
                when (e.status.description) {
                    "user name not match" -> {
                        return LoginResult.Failure.UserNotFound
                    }
                    "user password not match" -> {
                        return LoginResult.Failure.InvalidPassword
                    }
                }
            }
            e.printStackTrace()
            LoginResult.Failure.UnknownError
        } catch (e: Exception) {
            e.printStackTrace()
            LoginResult.Failure.UnknownError
        }
    }
}
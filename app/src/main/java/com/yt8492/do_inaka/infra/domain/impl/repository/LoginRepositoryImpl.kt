package com.yt8492.do_inaka.infra.domain.impl.repository

import android.util.Log
import com.yt8492.do_inaka.domain.model.*
import com.yt8492.do_inaka.domain.repository.LoginRepository
import com.yt8492.do_inaka.infra.api.DoInakaService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val doInakaService: DoInakaService
) : LoginRepository {
    override suspend fun login(username: Username, password: Password): LoginUser {
        val response = doInakaService.login(username.value, password.value)
        val type = when (response.role) {
            1L -> UserType.Requester
            2L -> UserType.Driver
            else -> {
                Log.d("hogehoge", "role: ${response.role}")
                throw IllegalStateException("Unknown Role ID")
            }
        }
        val loginUser = LoginUser(
            UserToken(response.token),
            type
        )
        return loginUser
    }
}
package com.yt8492.do_inaka.infra.domain.impl.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.yt8492.do_inaka.domain.model.LoginUser
import com.yt8492.do_inaka.domain.model.UserToken
import com.yt8492.do_inaka.domain.model.UserType
import com.yt8492.do_inaka.domain.repository.LoginUserRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class LoginUserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LoginUserRepository {

    override suspend fun getLoginUser(): LoginUser? {
        val token = sharedPreferences.getString(KEY_USER_TOKEN, null)
            ?.let {
                UserToken(it)
            }
        val type = sharedPreferences.getLong(KEY_USER_TYPE, 0L)
        return if (token != null) {
            val userType = when (type) {
                ROLE_REQUESTER -> UserType.Requester
                ROLE_DRIVER -> UserType.Driver
                else -> throw IllegalArgumentException("Unknown UserType")
            }
            LoginUser(token, userType)
        } else {
            null
        }
    }

    override suspend fun saveLoginUser(user: LoginUser) {
        sharedPreferences.edit {
            putString(KEY_USER_TOKEN, user.userToken.value)
            val type = when (user.userType) {
                UserType.Requester -> ROLE_REQUESTER
                UserType.Driver -> ROLE_DRIVER
            }
            putLong(KEY_USER_TYPE, type)
        }
    }

    companion object {
        private const val KEY_USER_TOKEN = "UserToken"
        private const val KEY_USER_TYPE = "UserType"
        private const val ROLE_REQUESTER = 1L
        private const val ROLE_DRIVER = 2L
    }
}
package com.yt8492.do_inaka.infra.domain.impl.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.yt8492.do_inaka.domain.model.UserToken
import com.yt8492.do_inaka.domain.repository.AuthTokenRepository
import javax.inject.Inject

class AuthTokenRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthTokenRepository {

    override suspend fun getToken(): UserToken? =
        sharedPreferences.getString(KEY_TOKEN, null)
            ?.let {
                UserToken(it)
            }

    override suspend fun saveToken(token: UserToken) {
        sharedPreferences.edit {
            putString(KEY_TOKEN, token.value)
        }
    }

    companion object {
        private const val KEY_TOKEN = "AuthToken"
    }
}
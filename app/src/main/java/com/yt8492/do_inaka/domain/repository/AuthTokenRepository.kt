package com.yt8492.do_inaka.domain.repository

import com.yt8492.do_inaka.domain.model.UserToken

interface AuthTokenRepository {
    suspend fun getToken(): UserToken?
    suspend fun saveToken(token: UserToken)
}
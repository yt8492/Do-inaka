package com.yt8492.do_inaka.domain.repository

interface AuthTokenRepository {
    fun getToken(): String?
}
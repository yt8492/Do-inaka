package com.yt8492.do_inaka.domain.model

sealed class UserType {
    object Driver : UserType()
    object Requester : UserType()
}
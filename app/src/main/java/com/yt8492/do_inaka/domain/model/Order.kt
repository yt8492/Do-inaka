package com.yt8492.do_inaka.domain.model

data class Order(
    val postNumber: String,
    val address: String,
    val items: List<String>
)
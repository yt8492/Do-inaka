package com.yt8492.do_inaka.domain.repository

import com.yt8492.do_inaka.domain.model.Driver
import com.yt8492.do_inaka.domain.model.Latitude
import com.yt8492.do_inaka.domain.model.Longitude
import com.yt8492.do_inaka.domain.model.Order

interface DriverRepository {

    suspend fun sendCurrentPlace(latitude: Latitude, longitude: Longitude)

    suspend fun getMyProfile(): Driver

    suspend fun getOrderList(): List<Order>
}
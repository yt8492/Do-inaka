package com.yt8492.do_inaka.domain.repository

import com.yt8492.do_inaka.domain.model.*
import com.yt8492.do_inaka.protobuf.Empty

interface RequesterRepository {

    suspend fun getNearByDriversLocation(latitude: Latitude, longitude: Longitude): List<Driver>

    suspend fun sendOrder(order: Order): Empty

    suspend fun evalDriver(driverId: UserId, score: ReliabilityScore)
}
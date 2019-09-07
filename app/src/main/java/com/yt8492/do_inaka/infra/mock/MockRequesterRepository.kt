package com.yt8492.do_inaka.infra.mock

import com.yt8492.do_inaka.domain.model.*
import com.yt8492.do_inaka.domain.repository.RequesterRepository
import com.yt8492.do_inaka.protobuf.Empty

object MockRequesterRepository : RequesterRepository {

    override suspend fun evalDriver(driverId: UserId, score: ReliabilityScore) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getNearByDriversLocation(
        latitude: Latitude,
        longitude: Longitude
    ): List<Driver> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun sendOrder(order: Order): Empty {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package com.yt8492.do_inaka.infra.mock

import com.yt8492.do_inaka.domain.model.Driver
import com.yt8492.do_inaka.domain.model.Latitude
import com.yt8492.do_inaka.domain.model.Longitude
import com.yt8492.do_inaka.domain.model.Order
import com.yt8492.do_inaka.domain.repository.DriverRepository

object MockDriverRepository : DriverRepository {

    override suspend fun getMyProfile(): Driver {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getOrderList(): List<Order> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun sendCurrentPlace(latitude: Latitude, longitude: Longitude) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
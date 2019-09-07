package com.yt8492.do_inaka.infra.api;

import com.yt8492.do_inaka.domain.repository.AuthTokenRepository;

import javax.inject.Inject;
import javax.inject.Named;

public class DoInakaService {

    @Inject
    public DoInakaService(
            @Named("Address") String address,
            @Named("Port") int port,
            AuthTokenRepository repository
    ) {

    }
}

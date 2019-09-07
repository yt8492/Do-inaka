package com.yt8492.do_inaka.infra.api;

import com.yt8492.do_inaka.domain.repository.AuthTokenRepository;
import com.yt8492.do_inaka.protobuf.CommonServiceGrpc;
import com.yt8492.do_inaka.protobuf.Driver;
import com.yt8492.do_inaka.protobuf.DriverOrBuilder;
import com.yt8492.do_inaka.protobuf.DriverServiceGrpc;
import com.yt8492.do_inaka.protobuf.Drivers;
import com.yt8492.do_inaka.protobuf.Empty;
import com.yt8492.do_inaka.protobuf.LoginRequest;
import com.yt8492.do_inaka.protobuf.LoginResponse;
import com.yt8492.do_inaka.protobuf.Order;
import com.yt8492.do_inaka.protobuf.Orders;
import com.yt8492.do_inaka.protobuf.Position;

import javax.inject.Inject;
import javax.inject.Named;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DoInakaService {

    String address;
    int port;
    AuthTokenRepository repository;

    @Inject
    public DoInakaService(
            @Named("Address") String address,
            @Named("Port") int port,
            AuthTokenRepository repository
    ) {
        this.address = address;
        this.port = port;
        this.repository = repository;
    }

    private CommonServiceGrpc.CommonServiceBlockingStub commonServiceStub = CommonServiceGrpc.newBlockingStub(ManagedChannelBuilder.forAddress(address, port)
            .usePlaintext()
            .build());

    public LoginResponse login(String userName, String password)
    {
        //request(LoginRequest)
        LoginRequest req = LoginRequest.newBuilder()
                .setId(userName)
                .setPassword(password)
                .build();
        //response(LoginResponse)
        LoginResponse res = commonServiceStub.login(req);


        return res;
    }

    public void sendCurrentPlace(String token, String latitude, String longitude){
        //request(position)
        Position req = Position.newBuilder()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .build();
        //res(Empty)
    }


    public Driver getMyProfile(String token){
        //req(Empty)
        Empty req = Empty.newBuilder().build();
        //res(Driver)
        ManagedChannel privateChannel = makePrivateChannel(token);
        DriverServiceGrpc.DriverServiceBlockingStub privateStub = DriverServiceGrpc.newBlockingStub(privateChannel);
        Driver res = privateStub.getMyProfile(req);
        return res;
    }

    public Orders getOrder(String token){
        //req(Empty)
        Empty req = Empty.newBuilder().build();
        //res(Order)
        ManagedChannel privateChannel = makePrivateChannel(token);
        DriverServiceGrpc.DriverServiceBlockingStub privateStub = DriverServiceGrpc.newBlockingStub(privateChannel);
        Orders res = privateStub.getOrder(req);
        return res;
    }

//    public Drivers getNearDriversLocation(String token, String latitude, String longitude){
//        //req()
//    }

    public ManagedChannel makePrivateChannel(String token){
        return ManagedChannelBuilder.forAddress(address, port)
                .intercept(new AuthInterceptor(token))
                .usePlaintext()
                .build();
    }


}
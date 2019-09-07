package com.yt8492.do_inaka.infra.api;

import com.yt8492.do_inaka.protobuf.CommonServiceGrpc;
import com.yt8492.do_inaka.protobuf.Driver;
import com.yt8492.do_inaka.protobuf.DriverServiceGrpc;
import com.yt8492.do_inaka.protobuf.Drivers;
import com.yt8492.do_inaka.protobuf.Empty;
import com.yt8492.do_inaka.protobuf.EvalDriverRequest;
import com.yt8492.do_inaka.protobuf.LoginRequest;
import com.yt8492.do_inaka.protobuf.LoginResponse;
import com.yt8492.do_inaka.protobuf.Order;
import com.yt8492.do_inaka.protobuf.Orders;
import com.yt8492.do_inaka.protobuf.Position;
import com.yt8492.do_inaka.protobuf.Requester;
import com.yt8492.do_inaka.protobuf.RequesterServiceGrpc;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DoInakaService {

    private String address;
    private int port;

    private CommonServiceGrpc.CommonServiceBlockingStub commonServiceStub;

    @Inject
    public DoInakaService(
            @Named("Address") String address,
            @Named("Port") int port
    ) {
        this.address = address;
        this.port = port;
        commonServiceStub  = CommonServiceGrpc.newBlockingStub(
                ManagedChannelBuilder
                        .forAddress(
                                address,
                                port
                        )
                        .usePlaintext()
                        .build()
        );
    }

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

        DriverServiceGrpc.newBlockingStub(makePrivateChannel(token))
                .sendCurrentPlace(req);
        //res(Empty)
    }


    public Driver getDriverProfile(String token){
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

    public List<Driver> getNearByDriversLocation(String token, String latitude, String longitude){
        //req(Position)
        Position req = Position.newBuilder()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .build();
        //res(Drivers)
        ManagedChannel privateChannel = makePrivateChannel(token);
        RequesterServiceGrpc.RequesterServiceBlockingStub privateStub = RequesterServiceGrpc.newBlockingStub(privateChannel);
        Drivers res = privateStub.getNearByDriversLocation(req);
        return res.getDriverList();
    }

    public void chumon(String token, String postNumber, String address ,List<String> items){
        //req(Order)
        Order req = Order.newBuilder()
                .setPostNumber(postNumber)
                .setAddress(address)
                .addAllItems(items)
                .build();
        RequesterServiceGrpc.newBlockingStub(makePrivateChannel(token))
                .chumon(req);
        //res(Empty)
    }

    public void evalDriver(String token, String userId, long reliabilityScore){
        //req(EvalDriverRequest)
        EvalDriverRequest req = EvalDriverRequest.newBuilder()
                .setUserId(userId)
                .setReliabilityScore(reliabilityScore)
                .build();

        RequesterServiceGrpc.newBlockingStub(makePrivateChannel(token))
                .evalDriver(req);
        //res(Empty)
    }

    public Requester getRequesterProfile(String token){
        //req(Empty)
        Empty req = Empty.newBuilder().build();
        //res(Requester)
        ManagedChannel privateChannel = makePrivateChannel(token);
        RequesterServiceGrpc.RequesterServiceBlockingStub privateStub = RequesterServiceGrpc.newBlockingStub(privateChannel);
        Requester res = privateStub.getMyProfile(req);
        return res;
    }

    public ManagedChannel makePrivateChannel(String token){
        return ManagedChannelBuilder.forAddress(address, port)
                .intercept(new AuthInterceptor(token))
                .usePlaintext()
                .build();
    }

}
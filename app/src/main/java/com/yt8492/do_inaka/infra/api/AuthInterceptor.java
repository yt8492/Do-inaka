package com.yt8492.do_inaka.infra.api;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

public class AuthInterceptor implements ClientInterceptor {

    public AuthInterceptor(String token) {

    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next
    ) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)){
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers){
                headers.put(CUSTOM_HEADER_KEY, "Bearer $token");
                super.start(responseListener, headers);
            }
        };

    }

    private static Metadata.Key<String> CUSTOM_HEADER_KEY = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
}

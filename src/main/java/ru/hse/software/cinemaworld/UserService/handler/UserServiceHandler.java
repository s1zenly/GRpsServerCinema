package ru.hse.software.cinemaworld.UserService.handler;

import io.grpc.stub.StreamObserver;
import ru.hse.software.grpc.UserServiceGrpc;
import ru.hse.software.grpc.UserServiceOuterClass;

public class UserServiceHandler extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void register(UserServiceOuterClass.APIRequest registerRequest,
                         StreamObserver<UserServiceOuterClass.APIResponse> registerResponse) {

        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        // Checking database on dublicate && checking on correct data

        UserServiceOuterClass.APIResponse.Builder response = UserServiceOuterClass.APIResponse.newBuilder();

        if(username.equals(password)) {
            response.setResponseCode(0).setResponseMessage("SUCCESS").build();
        } else {
            response.setResponseCode(100).setResponseMessage("FAILURE").build();
        }

        registerResponse.onNext(response.build());
        registerResponse.onCompleted();
    }

    @Override
    public void login(UserServiceOuterClass.APIRequest loginRequest,
                      StreamObserver<UserServiceOuterClass.APIResponse> loginResponse) {

    }

    public void logout(UserServiceOuterClass.Empty logoutRequest,
                       StreamObserver<UserServiceOuterClass.APIResponse> logoutResponse) {

    }
}

package ru.hse.software.cinemaworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hse.software.cinemaworld.UserService.handler.UserServiceHandler;
import ru.hse.software.cinemaworld.jwt.JwtAuthProvider;

import java.io.IOException;

public class GRpcServerApplication
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new UserServiceHandler())
                .addService(new AuthGrpsService())
                .build();

        server.start();

        System.out.println("Server started");

        server.awaitTermination();
    }
}

package ru.hse.software.cinemaworld;

import io.grpc.stub.StreamObserver;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.hse.software.cinemaworld.jwt.JwtAuthProvider;
import ru.hse.software.grpc.AuthServiceGrpc;
import ru.hse.software.grpc.AuthServiceOuterClass;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

public class AuthGrpsService extends AuthServiceGrpc.AuthServiceImplBase {

    private JwtAuthProvider jwtAuthProvider;

    AuthGrpsService() {}
    AuthGrpsService(JwtAuthProvider jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
    }

    @Override
    public void authenticate(AuthServiceOuterClass.JwtRequest requestAuth, StreamObserver<AuthServiceOuterClass.JWToken> responseAuth) {

        Authentication authenticate = jwtAuthProvider.authenticate(
                new UsernamePasswordAuthenticationToken(requestAuth.getUsername(), requestAuth.getPassword()));

        String authorities = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Instant now = Instant.now();
        Instant expiration = now.plus(1, ChronoUnit.HOURS);

        responseAuth.onNext(AuthServiceOuterClass.JWToken.newBuilder().setJwtToken(Jwts.builder()
                .setSubject((String) authenticate.getPrincipal())
                .claim("auth", authorities)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS512, " ")
                .compact()).build());

        responseAuth.onCompleted();
    }
}

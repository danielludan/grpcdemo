package org.example.grpc.unary.greeting;

import io.grpc.stub.StreamObserver;
import org.example.grpc.greeting.grpc.greeting.GreetingServiceGrpc;
import org.example.grpc.greeting.grpc.greeting.HelloRequest;
import org.example.grpc.greeting.grpc.greeting.HelloResponse;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(HelloRequest request,
                         StreamObserver<HelloResponse> responseObserver) {
        // HelloRequest has toString auto-generated.
        System.out.println(request);

        // You must use a builder to construct a new Protobuffer object
        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting("Hello there, " + request.getName())
                .build();

        // Use responseObserver to send a single response back
        responseObserver.onNext(response);

        // When you are done, you must call onCompleted.
        responseObserver.onCompleted();
    }
}

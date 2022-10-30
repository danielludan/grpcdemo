package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.greeting.GreetingServiceGrpc;
import org.example.grpc.greeting.GreetingServiceOuterClass;
import org.example.grpc.greeting.HelloRequest;
import org.example.grpc.greeting.HelloResponse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        // It is up to the client to determine whether to block the call
        // Here we create a blocking stub, but an async stub,
        // or an async stub with Future are always possible.
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        HelloRequest request =
                HelloRequest.newBuilder()
                        .setName("Ray")
                        .build();

        // Finally, make the call using the stub
        HelloResponse response =
                stub.greeting(request);

        System.out.println(response);

        // A Channel should be shutdown before stopping the process.
        channel.shutdownNow();
    }
}

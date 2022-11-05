/*
 * Copyright 2017 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.grpc.stream.greeting;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.stream.greeting.observer.ClientStreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class StreamGreetingServiceClient {
    private static final Logger logger =
            Logger.getLogger(StreamGreetingServiceClient2.class.getName());

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(1);

        // Create a channel and a stub
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .keepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTimeout(5, TimeUnit.SECONDS)
                .keepAliveWithoutCalls(true)
                .build();
//      ManagedChannel channel2 = ManagedChannelBuilder
//              .forAddress("localhost", 8080)
//              .usePlaintext()
//              .keepAliveTime(10, TimeUnit.SECONDS)
//              .keepAliveTimeout(5, TimeUnit.SECONDS)
//              .keepAliveWithoutCalls(true)
//              .build();
//        StreamingGreeterGrpc.StreamingGreeterStub stub = StreamingGreeterGrpc.newStub(channel);

//        StreamingGreeterGrpc.StreamingGreeterFutureStub stub1 = StreamingGreeterGrpc.newFutureStub(channel);

        // When using manual flow-control and back-pressure on the client, the ClientResponseObserver handles both
        // request and response streams.


        // Note: clientResponseObserver is handling both request and response stream processing.
//        stub.sayHelloStreaming(new ClientStreamObserver(done));
        System.out.println(String.format("channel2State:" + String.valueOf(channel.getState(false))));
//    System.out.println(String.format("channel2State:" + String.valueOf(channel2.getState(false))));
        done.await();

//    channel.shutdown();
//    channel.awaitTermination(1, TimeUnit.SECONDS);
        while (true) {
            Thread.sleep(500);
            System.out.println(String.format("channel1State:" + String.valueOf(channel.getState(false))));
//        System.out.println(String.format("channel2State:" + String.valueOf(channel2.getState(false))));
        }
    }

}

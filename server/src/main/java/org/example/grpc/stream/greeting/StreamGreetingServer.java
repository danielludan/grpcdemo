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

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class StreamGreetingServer {
  private static final Logger logger =
      Logger.getLogger(StreamGreetingServer.class.getName());

  public static void main(String[] args) throws InterruptedException, IOException {

    final Server server = ServerBuilder
        .forPort(9999)
        .addService(new StreamGreetingServiceImpl())
//            .keepAliveTime(10, TimeUnit.SECONDS)
//            .keepAliveTimeout(5, TimeUnit.SECONDS)
//            .permitKeepAliveWithoutCalls(true)
//            .permitKeepAliveTime(10, TimeUnit.SECONDS)
        .build()
        .start();

    System.out.println("服务在端口" + server.getPort() + "启动");

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("服务关闭");
        try {
          server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
      }
    });
    server.awaitTermination();
  }
}

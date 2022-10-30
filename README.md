# Purpose
## Modules
There are two modules:
<li>Server: GRPC server</li>
<li>Client: GRPC client</li>

## Naming Convention
<b>Packages</b>
<li><code>org.example.grpc.unary</code>: Request and Response unary communication API</li>
<li><code>org.example.grpc.stream</code>: Stream communication API</li>

<b>GRPC Server Class Name</b>
<li>XXXServer[optional number] : Class to start GRPC server, if there is a number suffixed means it is a duplicated server with different port</li>

<b>GRPC Client Class Name</b>
<li>XXXClient[optional number] : Client to establish connection and communicate with GRPC server, if there is a number suffixed means it is a duplicated client with different port</li>

## IDL
<b>Location</b> : <code>src/main/proto</code>
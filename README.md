## Purpose
Learn GRPC by practice
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

## Learning Marks
### Bidirectional Stream API
1. By default, each channel being created map to one TCP connection, you can use <code>netstat</code> to verify
2. Channel can be reused by multiple stream, each stream on the same connection has different stream ID, you can use <code>wireshark</code> capturing HTTP2 package to verify
3. If channel is not shutdown, on linux system with default tcp keepalive setting, the TCP keepalive probe should be sent after 7200 seconds, you can use <code>wireshark</code> to see there is no TCP probe when no RPC call
4. Both GRPC server and GRPC client has GRPC ping setting and the setting of the ping on both side should consider setting on another side. Below are the basic configuration on both side
Detailed info please ref to https://grpc.github.io/grpc-java/javadoc/
   <code>keepAliveTime</code>
   <code>keepAliveTimeout</code>
   <code>permitKeepAliveWithoutCalls</code>
   <code>permitKeepAliveTime</code>
5. From GRPC best practice, client and server should use PING rather than native TPC keepalive to keep the GRPC connection alive  
6. Stub is lightweight object with can be freely created, each stub must attach a channel to call API
7. ManagedChannel has <code>getState</code> API to get its state


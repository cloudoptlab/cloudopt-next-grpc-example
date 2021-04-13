package net.cloudopt.next.grpc.example

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.vertx.core.Future
import io.vertx.grpc.VertxChannelBuilder
import kotlinx.coroutines.runBlocking
import net.cloudopt.next.grpc.example.proto.GreeterGrpc
import net.cloudopt.next.grpc.example.proto.HelloReply
import net.cloudopt.next.grpc.example.proto.HelloRequest
import net.cloudopt.next.grpc.example.proto.sayHello
import net.cloudopt.next.web.Worker
import net.cloudopt.next.web.Worker.global

val localhost: ManagedChannel = VertxChannelBuilder.forAddress(Worker.vertx, "127.0.0.1", 9090)
    .usePlaintext()
    .build()
val greeter: GreeterGrpc.GreeterStub = GreeterGrpc.newStub(localhost)

suspend fun main() {
    Worker.setTimer(200,true){
        global {
            testConnection()
        }
    }
}

suspend fun testConnection() {
    val request: HelloRequest = HelloRequest.newBuilder().setName("Next").build()
    val reply = greeter.sayHello(request)
    println(reply.message)
}

package net.cloudopt.next.grpc.example

import net.cloudopt.next.grpc.GrpcService
import net.cloudopt.next.grpc.example.proto.HelloReply
import net.cloudopt.next.grpc.example.proto.HelloRequest
import net.cloudopt.next.grpc.example.proto.GreeterImplBase
import net.cloudopt.next.web.Worker
import net.cloudopt.next.web.Worker.await

@GrpcService
class HelloWordService : GreeterImplBase(coroutineContext = Worker.dispatcher()) {

    override suspend fun sayHello(request: HelloRequest): HelloReply {
        return await { promise ->
            promise.complete(
                HelloReply.newBuilder()
                    .setMessage(request.name)
                    .build()
            )
        }
    }

}
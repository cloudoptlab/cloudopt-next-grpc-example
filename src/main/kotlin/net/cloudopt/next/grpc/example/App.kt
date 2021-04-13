package net.cloudopt.next.grpc.example

import net.cloudopt.next.grpc.GrpcPlugin
import net.cloudopt.next.web.NextServer


fun main() {
    NextServer.addPlugin(GrpcPlugin())
    NextServer.run()
}
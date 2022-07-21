import java.net.ServerSocket
import kotlin.concurrent.thread

fun main() {
    val server = ServerSocket(12345)
    println("Server is running on port ${server.localPort}")

    //client that is coming from outside
    val client = server.accept()
    println("Client connected: host ${client.inetAddress.hostAddress} port ${client.localPort}")

    val clientOutStream = client.getOutputStream()
    val clientInStream = client.getInputStream()

    thread {
        while (true) {
            val nextByte = clientInStream.read()
            print(nextByte.toChar())
        }
    }

    thread {
        while (true) {
            val input = readLine() ?: ""
            clientOutStream.write(input.toByteArray())
        }
    }
}
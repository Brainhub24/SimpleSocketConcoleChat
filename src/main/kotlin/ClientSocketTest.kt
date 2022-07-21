import java.net.Socket
import kotlin.concurrent.thread

fun main() {
    val client = Socket("127.0.0.1", 12345)

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
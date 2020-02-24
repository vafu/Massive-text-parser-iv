import java.io.InputStream

interface MassiveTextParser {

    fun parse(textStreams: List<InputStream>): Map<String, Int>
}

import java.io.InputStream

interface MassiveTextParser {

    /**
     * Processes input [textStreams] and counts total amount of unique words.
     * Note: "uniqueness" is case-insensitive.
     */
    fun parse(textStreams: List<InputStream>): Map<String, Int>
}

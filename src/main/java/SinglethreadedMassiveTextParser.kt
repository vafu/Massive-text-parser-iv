import java.io.InputStream

object SingleThreadedMassiveTextParser : MassiveTextParser {
    override fun parse(textStreams: List<InputStream>): Map<String, Int> {
        val map = mutableMapOf<String, Int>()

        textStreams.forEach { textStream ->
            textStream.bufferedReader().use {
                it.readText().split(whitespaceRegex)
                    .forEach { word ->
                        val key = word
                            .replace(notAWordRegex, "")
                            .trim()
                            .toLowerCase()

                        map.put(key, map[key]?.inc() ?: 1)
                    }
            }
        }

        return map
    }
}

private val notAWordRegex = "[^a-zA-Z0-9]".toRegex()
private val whitespaceRegex = "\\s+".toRegex()

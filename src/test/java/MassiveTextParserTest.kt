import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

private const val REPEAT_TIMES = 2

private val expectedResults = mapOf(
        "hamlet" to 100 * REPEAT_TIMES,
        "king" to 171 * REPEAT_TIMES,
        "a" to 497 * REPEAT_TIMES,
        "." to 0,
        "snapchat" to 0
)

class MassiveTextParserTest {

    private val subject = MassiveTextParserFactory.create()

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    private lateinit var files: List<File>

    @Before
    fun setUp() {
        temporaryFolder.create()
        val sourceFile =  File(javaClass.getResource("hamlet").toURI())
        files = List(REPEAT_TIMES) {
            val targetFile = temporaryFolder.newFile("hamlet$it")
            sourceFile.copyTo(targetFile, overwrite = true)
        }
    }

    @After
    fun tearDown() {
        temporaryFolder.delete()
    }

    @Test
    fun `parse hamlet REPEAT_TIMES, correct word count`() {
        val actualResults = subject.parse(files.map { it.inputStream() })

        expectedResults.forEach { word, expectedCount ->
            val actualCount = actualResults[word] ?: 0
            assert(expectedCount == actualCount) {
                "$word - expected:$expectedCount, actual:$actualCount"
            }
        }
    }
}

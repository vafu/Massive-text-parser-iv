object MassiveTextParserFactory {

    fun create(): MassiveTextParser =
            SinglethreadedMassiveTextParser()
}

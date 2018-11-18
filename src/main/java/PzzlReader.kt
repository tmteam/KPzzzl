import com.esotericsoftware.yamlbeans.YamlReader

class PzzlReader {
    fun read(): PzzlesConfig {
        val fileContent = this
                .javaClass
                .getResource("base.pzzl")
                .readText()

        val reader = YamlReader(fileContent)
        return reader.read(PzzlesConfig::class.java)
    }
}
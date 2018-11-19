import com.esotericsoftware.yamlbeans.YamlReader
import java.io.File

class PzzlReader {
    fun readDefault(): PzzlesConfig {
        val fileContent = this
                .javaClass
                .getResource("base.pzzl")
                .readText()

        val reader = YamlReader(fileContent)
        return reader.read(PzzlesConfig::class.java)
    }
    fun read(path: String): PzzlesConfig {
        val content = File(path).readText()

        val reader = YamlReader(content)
        return reader.read(PzzlesConfig::class.java)
    }
}
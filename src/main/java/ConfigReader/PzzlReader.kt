package ConfigReader

import com.esotericsoftware.yamlbeans.YamlReader
import java.io.File
import java.net.URL

class PzzlReader {
    fun readResource(url: URL): PzzlesConfig {
        val fileContent = url.readText()

        val reader = YamlReader(fileContent)
        return reader.read(PzzlesConfig::class.java)
    }
    fun readDefault(): PzzlesConfig {
        val url = this
                .javaClass
                .getResource("base.pzzl")
        return readResource(url)
    }
    fun read(path: String): PzzlesConfig {
        val content = File(path).readText()

        val reader = YamlReader(content)
        return reader.read(PzzlesConfig::class.java)
    }
}
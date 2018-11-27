package ConfigReader

import java.util.*

class PzzlModuleConfig{
    var name: String? = null
    var command: String? = null
    var argument: String? = null
    var arguments: List<String>? = null
    var mode: String? = null
    //No ideas how to make it better via YamlBeans. -)
    var environment: ArrayList<LinkedHashMap<String, String>>? = null
    var autostart = true
    var dir: String? = null
}


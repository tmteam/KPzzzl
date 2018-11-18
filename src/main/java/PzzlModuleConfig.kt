class PzzlModuleConfig{
    var name: String? = null
    var command: String? = null
    var argument: String? = null
    var mode: String? = null
    //No ideas how to make it better via YamlBeans. -)
    var environment: ArrayList<LinkedHashMap<String,String>>? = null

}


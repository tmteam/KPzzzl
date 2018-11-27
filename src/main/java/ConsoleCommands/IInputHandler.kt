package ConsoleCommands


interface IInputHandler{
    val name: String
    fun getHelp(): String
    fun getInputArgumentsHelp(): String
    fun tryProcess(input: String)
}



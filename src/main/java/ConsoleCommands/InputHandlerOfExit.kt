package ConsoleCommands

class InputHandlerOfExit: IInputHandler {
    var wasExitAsked: Boolean = false

    override val name: String ="exit"
    override fun getHelp(): String = "Closes the programm"

    override fun getInputArgumentsHelp(): String =""

    override fun tryProcess(input: String){
        wasExitAsked = true;
    }
}
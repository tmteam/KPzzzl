package ConsoleCommands

class InputCommandProcessor(val handlers: Array<IInputHandler>) {

    fun ProcessSingle(input: String): Boolean{
        for (handler in handlers){
            val name = handler.name.toLowerCase()
            if(name== input.toLowerCase() || input.startsWith(name+' ')){
                val trimmed = input.removeRange(0,name.length).trim();
                handler.tryProcess(trimmed)
                return true
            }
        }
        return false
    }
}
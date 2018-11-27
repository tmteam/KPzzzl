package ConsoleCommands

import java.io.PrintStream

class InputHandlerOfHelp
    (private val output: PrintStream, val otherProcessors: Array<IInputHandler>)
    : IInputHandler {
    override val name = "help";
    override fun getHelp() = "shows this message"

    override fun getInputArgumentsHelp() = ""

    override fun tryProcess(input: String) {
        println("PZZL commands:")
        for (handler in otherProcessors){
            printDescriptionString(handler)
        }
        printDescriptionString(this)
    }

    private fun printDescriptionString(handler: IInputHandler) {
        val argHelp = if (handler.getInputArgumentsHelp()=="") ""
        else " [${handler.getInputArgumentsHelp().fix(12)}] "

        output.println("\t${handler.name.fix(5)}${argHelp}- ${handler.getHelp()}")
    }
}
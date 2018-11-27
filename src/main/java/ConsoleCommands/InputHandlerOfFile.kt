package ConsoleCommands

import java.io.PrintStream

class InputHandlerOfFile(
        private val text: String,
        private val fileName: String,
        private val output: PrintStream) : IInputHandler {

    override val name: String = "file"

    override fun getInputArgumentsHelp(): String = ""
    override fun getHelp(): String = "shows configuration file contents"

    override fun tryProcess(input: String) {
        output.println("------ PZZZL FILE $fileName ------")
        output.println()
        output.println(text)
        output.println()
        output.println("------ END OF $fileName ------")

    }

}
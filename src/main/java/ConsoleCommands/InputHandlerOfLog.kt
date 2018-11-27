package ConsoleCommands

import Runner.PieceLauncher
import Runner.PzzlRunner
import java.io.PrintStream

class InputHandlerOfLog
(runner: PzzlRunner, output: PrintStream)
    : InputHandlerForConcreteElementBase(runner, output) {

    override val name: String = "log"
    override fun getHelp(): String = "shows the output of target process"

    override fun Process(piece: PieceLauncher) {
        output.println()
        output.println("_".repeat(20)+ "[${piece.config.name}'s LOG ]"+"_".repeat(20))
        output.println(piece.getLog()?:"")
        output.println("_".repeat(20)+"[END OF ${piece.config.name}'s LOG]"+"_".repeat(20))
        output.println()
    }
}
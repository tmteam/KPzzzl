package ConsoleCommands

import Runner.PieceLauncher
import Runner.PzzlRunner
import java.io.PrintStream

class InputHandlerOfStop
(runner: PzzlRunner, output: PrintStream)
    : InputHandlerForConcreteElementBase(runner, output) {

    override val name: String = "stop"
    override fun getHelp(): String = "stops an application"

    override fun Process(piece: PieceLauncher) {
        output.println("stopping the ${piece.config.name}...")
        piece.stop()
        output.println("${piece.config.name} was stopped")
    }


}
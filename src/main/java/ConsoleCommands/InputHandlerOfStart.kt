package ConsoleCommands

import Runner.PieceLauncher
import Runner.PzzlRunner
import java.io.PrintStream

class InputHandlerOfStart
    (runner: PzzlRunner, output: PrintStream)
    : InputHandlerForConcreteElementBase(runner, output) {

    override val name: String = "start"
    override fun getHelp(): String = "starts an application"

    override fun Process(piece: PieceLauncher) {
        output.println("starting the ${piece.config.name}...")
        piece.start()
        output.println("${piece.config.name} was started")
    }


}
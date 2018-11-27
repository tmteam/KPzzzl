package ConsoleCommands

import Runner.PieceLauncher
import Runner.PzzlRunner
import tryParseInt
import java.io.PrintStream

abstract class InputHandlerForConcreteElementBase(
        protected val runner: PzzlRunner,
        protected val output: PrintStream) : IInputHandler {

    override fun getInputArgumentsHelp(): String = "piNum|piName"

    override fun tryProcess(input: String) {
        val piece =  tryGetPiece(input)
        if(piece==null)
            return
        Process(piece)
    }
    abstract fun Process(piece: PieceLauncher)

    protected fun tryGetPiece(input: String): PieceLauncher?{
        val id = input.removePrefix(name).trim()
        val idint = tryParseInt(id)

        val piece =
                if(idint==null) runner.get(id)
                else            runner.get(idint)
        if(piece == null)
            output.println( "\"$id\" is not valid piNum or piName")
        return piece
    }
}
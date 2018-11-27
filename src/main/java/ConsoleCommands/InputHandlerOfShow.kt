package ConsoleCommands

import Runner.PieceLauncher
import Runner.PzzlRunner
import java.io.PrintStream

class InputHandlerOfShow(
        private val runner: PzzlRunner,
        private val output: PrintStream) : IInputHandler {

    override val name: String = "show"

    override fun getInputArgumentsHelp(): String = ""
    override fun getHelp(): String = "shows current pieces state"

    override fun tryProcess(input: String) {
        output.println(renderPieceTable(runner.pieces))
    }

}


fun renderPieceTable(pieces: List<PieceLauncher>): String{
    val sb = StringBuilder()
    val colSize = 15
    sb.row(colSize, "piNum","piName","started")
    sb.appendln(" |" + ("-".repeat(rowLength(colSize,3)))+"|")

    for (piece in pieces) {
        sb.row(colSize, piece.piNum.toString(), piece.config!!.name!!, piece.isStarted.toString())
    }
    return sb.toString()
}
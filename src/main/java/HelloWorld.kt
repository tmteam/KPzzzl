import java.io.BufferedReader
import java.util.*
import kotlin.concurrent.timer

fun main(args: Array<String>) {
    val config = PzzlReader().read()
    val pieces = config.puzzles!!
            .mapIndexed { index, c ->  PieceLauncher(c, index) }

    val runner = PzzlRunner(pieces)

    runner.runAll()

    print(runner)
    println()
    printHelp()

    val s = Scanner(System.`in`.buffered());

    while(true)
    {
        val command = s.nextLine()
        if(!handleCommand(command, runner))
        {
            return
        }

    }

    runner.stopAll()

    System.`in`.read()


}

fun handleCommand(inputCommand:String, runner: PzzlRunner): Boolean{
    val cmd = inputCommand.toLowerCase()

    if(cmd == "exit") {

        println("Stoping every piece")
        runner.stopAll()
        println("Bye!")
        return false
    }
    else if(tryHandleCommand(cmd,runner))
        return true
    else
        System.out.println("Unknown command: $inputCommand")
    return true
}
fun tryHandleCommand(cmd:String, runner: PzzlRunner): Boolean{

    if(cmd == "show") {
        print(runner)
        return true
    }
    else if(cmd.startsWith("start ")){
        val id = cmd.removePrefix("start ").trim()
        val idint = tryParseInt(id)

        val piece =
                if(idint==null) runner.get(id)
                else            runner.get(idint)
        if(piece == null)
            println("\"$id\" is not valid piNum or piName")
        else
        {
            println("starting the $id...")
            piece.Start()
            println("$id was started")
        }
        return true
    }
    else if(cmd.startsWith("stop ")){
        val id = cmd.removePrefix("stop ").trim()
        val idint = tryParseInt(id)

        val piece =
                if(idint==null) runner.get(id)
                else            runner.get(idint)
        if(piece == null)
            println("\"$id\" is not valid piNum or piName")
        else {
            println("stopping the $id...")
            piece.Stop()
            println("$id was stopped")
        }
        return true
    }
    else if(cmd == "help"){
        printHelp()
        return true
    }

    return false;
}

private fun printHelp() {
    println("PZZL commands:")
    println("\tstart [piNum|piName] - starts piece by piNum or piName")
    println("\tstop  [piNum|piName] - stops  piece by piNum or piName")
    println("\tshow - shows current pieces state")
    println("\thelp - shows this message")
}

private fun print(runner: PzzlRunner) {
    println(renderPieceTable(runner.pieces))
}

fun tryParseInt(value: String): Int?{
    try {
        return Integer.parseInt(value)
    } catch (e: NumberFormatException) {
        return null
    }

}






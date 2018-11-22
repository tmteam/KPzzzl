
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
            piece.start()
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
            piece.stop()
            println("$id was stopped")
        }
        return true
    }
    else if(cmd.startsWith("log ")){
        val id = cmd.removePrefix("log ").trim()
        val idint = tryParseInt(id)

        val piece =
                if(idint==null) runner.get(id)
                else            runner.get(idint)
        if(piece == null)
            println("\"$id\" is not valid piNum or piName")
        else {
            println()
            println("_".repeat(20)+ "[${piece.config.name}'s LOG ]"+"_".repeat(20))
            println(piece.getLog()?:"")
            println("_".repeat(20)+"[END OF ${piece.config.name}'s LOG]"+"_".repeat(20))
            println()

        }
        return true
    }
    else if(cmd == "help"){
        printHelp()
        return true
    }

    return false;
}

fun printHelp() {
    println("PZZL commands:")
    println("\tstart [piNum|piName] - starts piece by piNum or piName")
    println("\tstop  [piNum|piName] - stops  piece by piNum or piName")
    println("\tlog   [piNum|piName] - show  piece output by piNum or piName")
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

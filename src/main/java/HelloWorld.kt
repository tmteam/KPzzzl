import java.util.*

fun main(args: Array<String>) {

    var filePath = tryParsePath(args)

    val config = TryLoadPzzl(filePath)
    if(config==null)
    {
        println("Pzzl file \"${filePath}\" not found. Exit")
        return
    }

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
            runner.stopAll()
            return
        }

    }

    return
}

private fun TryLoadPzzl(filePath: String?): PzzlesConfig? {
    if (filePath == null) {
        println("File path not specified. Loading defaults.")
        return PzzlReader().readDefault()

    }
    try {
        return PzzlReader().read(filePath!!)
    } catch (e: Exception) {
        return null
    }
}

fun tryParsePath(args: Array<String>): String?{
    for(arg in args ){
        val path = tryParsePath(arg)
        if(path!=null && !path.isEmpty())
            return path
    }
    return null
}

fun tryParsePath(arg: String): String?{
    if(!arg.startsWith("--path"))
        return null;
    var tail = arg.removePrefix("--path").trim()
    if(!tail.startsWith("="))
        return null;
    tail = tail.removePrefix("=").trim()
    if(tail.isEmpty())
        return null
    return tail
}




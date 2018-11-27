import ConfigReader.PzzlReader
import ConfigReader.PzzlesConfig
import ConsoleCommands.*
import Runner.PieceLauncher
import Runner.PzzlRunner
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    var filePath = tryParsePath(args)


    val config =  DefaultResourceReader().tryLoadPzzl(filePath)
    if(config==null)
    {
        System.out.println("Exit")
        return
    }
    val pieces = config.puzzles!!
            .mapIndexed { index, c -> PieceLauncher(c, index) }
    val runner = PzzlRunner(pieces)
    runner.runAll()
    print(runner)
    println()


    processUserInput(runner, Scanner(System.`in`.buffered()),filePath)
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

fun processUserInput(runner: PzzlRunner, scanner:Scanner, pzzlFilePath: String?){


    val handlers = LinkedList<IInputHandler>()
    handlers.add(InputHandlerOfStart(runner, System.out))
    handlers.add(InputHandlerOfStop(runner, System.out))
    handlers.add(InputHandlerOfLog(runner, System.out))
    handlers.add(InputHandlerOfShow(runner, System.out))

    val text = DefaultResourceReader().tryLoadPzzlAsText(pzzlFilePath)!!

    handlers.add(InputHandlerOfFile(text, pzzlFilePath?:"Default pzzl", System.out))

    val exitCommand = InputHandlerOfExit()
    handlers.add(exitCommand)
    val helpCommand = InputHandlerOfHelp(System.out, handlers.toTypedArray())
    handlers.add(helpCommand)

    helpCommand.tryProcess("")

    val commandProcessor = InputCommandProcessor(handlers.toTypedArray())

    while(true)
    {
        val command = scanner.nextLine()
        if(!commandProcessor.ProcessSingle(command)){
            System.out.println("Unknown command \"$command\". Type ${helpCommand.name} to see the options")
        }else if(exitCommand.wasExitAsked)
        {
            runner.stopAll()
            return
        }
    }
}

class DefaultResourceReader {
    fun tryLoadPzzlAsText(filePath: String?): String?{
        if (filePath == null) {
            println("File path not specified. Trying to load defaults.")
            try {
                return this.javaClass.getResource("base.pzzl").readText()
            }
            catch (e: Exception){
                println("Default configuration not specified.")
                return null
            }
        }
        try {
            return File(filePath).readText()
        } catch (e: Exception) {
            println("Pzzl file \"${filePath}\" not found. ")
            return null
        }
    }
    fun tryLoadPzzl(filePath: String?): PzzlesConfig? {
        val text = tryLoadPzzlAsText(filePath)
        if (text == null) {
            println("File is empty ")
            return null
        }
        try {
            return PzzlReader().readText(text)
        } catch (e: Exception) {
            println("File is  not valid pzzl file")
            return null
        }
    }
}



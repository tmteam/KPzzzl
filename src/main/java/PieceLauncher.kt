
import java.io.File

class PieceLauncher(
        val config: PzzlModuleConfig,
        val piNum: Int)
{
    private var p: Process? = null
    val isStarted: Boolean
        get() = if (p == null) false else p!!.isAlive

    private var log: File? = null

    fun getLog(): String { return if(log==null) "" else log!!.readText() }

    fun start(){
        val argss = ArrayList<String>()
        argss.add(config!!.command!!)
        if(config.argument!=null)
        argss.add(config!!.argument!!)
        if(config.arguments!=null)
        argss.addAll(config!!.arguments!!)

        val pb = ProcessBuilder(argss)

        if(config.dir!= null)
            pb.directory(File(config.dir))

        log = HookOutput(pb)

        SetEvironment(pb)
        p = pb.start()
    }


    fun stop(){
        p?.destroy()
    }

    private fun HookOutput(pb: ProcessBuilder): File {
        val log = File("log")
        log.deleteOnExit()

        pb.redirectErrorStream(true)
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log))
        return log
    }

    private fun SetEvironment(pb: ProcessBuilder) {
        if (config.environment != null) {
            for (env in config.environment!!)
                for (envpair in env)
                    pb.environment()[envpair.key] = envpair.value
        }
    }

}

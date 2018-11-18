
import java.io.File

class PieceLauncher(
        val config: PzzlModuleConfig,
        val piNum: Int)
{
    private var p: Process? = null
    val isStarted: Boolean
        get() = if (p == null) false else p!!.isAlive

    fun Start(){
        val pb = ProcessBuilder(config.command,config.argument)

        val log = HookOutput(pb)

        SetEvironment(pb)

       // pb.directory("myDir")
        p = pb.start()
        //Thread.sleep(1000)
        val logread = log.readText()
        //val value = p!!.exitValue()
        val isAlive = p!!.isAlive
    }


    fun Stop(){
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

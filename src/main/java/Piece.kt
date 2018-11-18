
import java.io.OutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.File

class Piece(_config: PzzlModuleConfig){
    val config: PzzlModuleConfig = _config
    private var p: Process? = null

    fun Start(){
        val pb = ProcessBuilder(config.command,config.argument)

        val log = File("log")
        log.deleteOnExit()

        pb.redirectErrorStream(true)
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log))

        if( config.environment!= null){
            for (env in config.environment!!)
                for(envpair in env)
                    pb.environment()[envpair.key] = envpair.value
        }
        /*
        val env = pb.environment()

        env["VAR1"] = "myValue"
        env.remove("OTHERVAR")
        env["VAR2"] = env["VAR1"] + "suffix"
        */
       // pb.directory("myDir")
        p = pb.start()
        Thread.sleep(1000)
        val logread = log.readText()
        //val value = p!!.exitValue()
        val isAlive = p!!.isAlive
    }
    fun Stop(){
        p?.destroy()
    }

}
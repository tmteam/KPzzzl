
import java.io.File
import java.io.IOException




class PieceLauncher(
        val config: PzzlModuleConfig,
        val piNum: Int)
{
    private var p: Process? = null
    val isStarted: Boolean
        get() = if (p == null) false else p!!.isAlive

    private var log: File? = null

    fun getLog(): String? { return if(log==null) null else tail(log!!,10) }

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

        log = HookOutput(pb, config.name!!)

        SetEvironment(pb)
        p = pb.start()
    }


    fun stop(){
        p?.destroy()
    }

    private fun HookOutput(pb: ProcessBuilder, name:String): File {
        val log = File("log_of_$name")
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
/*
    fun runCommandForOutput(params: List<String>): String {
        val pb = ProcessBuilder(params)
        val p: Process
        var result = ""
        try {
            p = pb.start()
            val reader = BufferedReader(InputStreamReader(p.inputStream))
            val sj = StringJoiner(System.getProperty("line.separator"))
            reader.lines().iterator().forEachRemaining { sj.add(it) }
            result = sj.toString()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }
*/

}
//https://stackoverflow.com/questions/686231/quickly-read-the-last-line-of-a-text-file
fun tail(file: File, lines: Int): String? {
    var fileHandler: java.io.RandomAccessFile? = null
    try {
        fileHandler = java.io.RandomAccessFile(file, "r")
        val fileLength = fileHandler.length() - 1
        val sb = StringBuilder()
        var line = 0

        for (filePointer in fileLength downTo -1 + 1) {
            fileHandler.seek(filePointer)
            val readByte = fileHandler.readByte().toInt()

            if (readByte == 0xA) {
                if (filePointer < fileLength) {
                    line += 1
                }
            } else if (readByte == 0xD) {
                if (filePointer < fileLength - 1) {
                    line += 1
                }
            }
            if (line >= lines) {
                break
            }
            sb.append(readByte.toChar())
        }

        return sb.reverse().toString()
    } catch (e: java.io.FileNotFoundException) {
        e.printStackTrace()
        return null
    } catch (e: java.io.IOException) {
        e.printStackTrace()
        return null
    } finally {
        if (fileHandler != null)
            try {
                fileHandler.close()
            } catch (e: IOException) {
            }

    }
}


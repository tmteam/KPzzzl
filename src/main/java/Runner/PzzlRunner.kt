package Runner

class PzzlRunner(val pieces: List<PieceLauncher>) {

    fun get(piNum: Int): PieceLauncher?{
        for (piece in pieces)
            if(piece.piNum == piNum)
                return piece
        return null
    }

    fun get(piName: String): PieceLauncher?{
        for (piece in pieces)
            if(piece.config.name!!.equals(piName, ignoreCase = true)  )
                return piece
        return null
    }

    fun runAll(){
        for(piece in pieces){
            if(piece.config.autostart)
            {
                piece.start()
                println("piece " + piece.config.name +" started")
            }
            else
                println("piece is not configured for autostart. Skip.")
        }
    }

    fun  stopAll(){
        for(piece in pieces){
            piece.stop()
            println("piece " + piece.config.name + " stopped")
        }
    }

}



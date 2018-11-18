class PzzlRunner(val pieces: List<PieceLauncher>) {

    fun get(piNum: Int):PieceLauncher?{
        for (piece in pieces)
            if(piece.piNum == piNum)
                return piece
        return null
    }

    fun get(piName: String):PieceLauncher?{
        for (piece in pieces)
            if(piece.config.name!!.equals(piName, ignoreCase = true)  )
                return piece
        return null
    }

    fun runAll(){
        for(piece in pieces){
            if(piece.config.autostart)
            {
                piece.Start()
                println("piece " + piece.config.name +" started")
            }
            else
                println("piece is not configured for autostart. Skip.")
        }
    }

    fun  stopAll(){
        for(piece in pieces){
            piece.Stop()
            println("piece " + piece.config.name + " stopped")
        }
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


fun rowLength(columnSize:Int, columnsCount: Int): Int{
    return (columnSize+2)*columnsCount +2
}
fun StringBuilder.row(columnSize:Int,vararg args: String) {
    for (arg in args) {
        this.append(" | ${arg.fix(columnSize)}")
    }
    this.appendln(" |")
}
fun Any?.fix(size: Int) = java.lang.String.format("%-${size}s", this)



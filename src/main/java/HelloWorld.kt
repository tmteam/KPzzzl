fun main(args: Array<String>) {

    val config = PzzlReader().read()
    val pieces = config.puzzles!!.map { c -> Piece(c) }

    for(piece in pieces){
        piece.Start()
        System.out.print("piece " + piece.config.name +" started")
    }

    System.`in`.read()

    for(piece in pieces){
        piece.Stop()
        System.out.print("piece " + piece.config.name + " stopped")
    }

    System.`in`.read()
}


import kotlin.concurrent.timer

fun main(args: Array<String>) {

    val config = PzzlReader().read()
    val pieces = config.puzzles!!.mapIndexed { index, c ->  PieceLauncher(c, index) }
    val runner = PzzlRunner(pieces)

    runner.runAll()

    val timer = timer(
            name = "render timer",
            initialDelay = 0,
            period = 1000,
            daemon = false,
            action = { System.out.println(renderPieceTable(runner.pieces))})

    System.`in`.read()

    runner.stopAll()

    timer.cancel()

    System.`in`.read()


}



fun renderPieceTable(): String {
    return ""
}



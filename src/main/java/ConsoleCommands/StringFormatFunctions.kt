package ConsoleCommands



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


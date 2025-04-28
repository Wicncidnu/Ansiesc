object AnsiColor {
    const val BLACK = 0
    const val RED = 1
    const val GREEN = 2
    const val YELLOW = 3
    const val BLUE = 4
    const val MAGENTA = 5
    const val CYAN = 6
    const val WHITE = 7
    const val BRIGHT_BLACK = 8
    const val BRIGHT_RED = 9
    const val BRIGHT_GREEN = 10
    const val BRIGHT_YELLOW = 11
    const val BRIGHT_BLUE = 12
    const val BRIGHT_MAGENTA = 13
    const val BRIGHT_CYAN = 14
    const val BRIGHT_WHITE = 15
}

class Ansi(private val text: String) {
    private var attributes = ArrayList<String>()

    override fun toString(): String {
        val att = attributes.fold("") { a, b ->
            "$a;$b"
        }
        return "\u001B[${att.substring(1, att.length)}m$text\u001B[0m"
    }

    fun bold(): Ansi {
        this.attributes.add("1")
        return this
    }

    fun faint(): Ansi {
        this.attributes.add("2")
        return this
    }

    fun italic(): Ansi {
        this.attributes.add("3")
        return this
    }

    fun underline(): Ansi {
        this.attributes.add("4")
        return this
    }

    /**
     * @param c range from 0 to 255
     */
    fun fgColor(c: Int): Ansi {
        require(c in 0..255) {
            val c = "c".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            "The value of $c must be within the range of 0 to 255."
        }
        this.attributes.add("38;5;$c")
        return this
    }

    /**
     * @param r range from 0 to 255
     * @param g range from 0 to 255
     * @param b range from 0 to 255
     */
    fun fgColor(r: Int, g: Int, b: Int): Ansi {
        require(r in 0..255 && g in 0..255 && b in 0..255) {
            val r = "r".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            val g = "g".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            val b = "b".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            "The values of $r,$g and $b must be within the range of 0 to 255."
        }
        this.attributes.add("38;2;$r;$g;$b")
        return this
    }

    /**
     * @param c range from 0 to 255
     */
    fun bgColor(c: Int): Ansi {
        require(c in 0..255) {
            val c = "c".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            "The value of $c must be within the range of 0 to 255."
        }
        this.attributes.add("48;5;$c")
        return this
    }

    /**
     * @param r range from 0 to 255
     * @param g range from 0 to 255
     * @param b range from 0 to 255
     */
    fun bgColor(r: Int, g: Int, b: Int): Ansi {
        require(r in 0..255 && g in 0..255 && b in 0..255) {
            val r = "r".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            val g = "g".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            val b = "b".rich { it.fgColor(AnsiColor.BRIGHT_RED).bold().italic() }
            "The values of $r,$g and $b must be within the range of 0 to 255."
        }
        this.attributes.add("48;2;$r;$g;$b")
        return this
    }
}

fun String.rich(edit: (text: Ansi) -> Ansi): Ansi {
    return edit(Ansi(this))
}
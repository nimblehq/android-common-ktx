package co.nimblehq.common.extensions

object StringExt {

    /*
     * Override String.isThai() extension
     * The [\\p{IsThai}] works with Java, So it will pass the Unit Test
     * But just [\\p{Thai}] works for Android, So we have to override it especially for the Unit Test
     * Ref: https://stackoverflow.com/a/47922877
     */
    @JvmStatic
    fun isThai(`this`: String): Boolean {
        print(`this`)
        val thaiRegex = "\\p{IsThai}".toRegex()
        return if (`this`.isEmpty()) false else `this`.all { thaiRegex.matches(it.toString()) }
    }
}

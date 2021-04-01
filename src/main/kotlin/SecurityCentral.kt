interface Closeable {
    var isOpened: Boolean

    fun close() {
        isOpened = false
    }

    fun open() {
        isOpened = true
    }
}

data class Window(override var isOpened: Boolean = true) : Closeable

data class Door(override var isOpened: Boolean = true) : Closeable

class SecurityCentral(private val window: Window, private val door: Door) {
    fun securityOn() {
        window.close()
        door.close()
    }
}
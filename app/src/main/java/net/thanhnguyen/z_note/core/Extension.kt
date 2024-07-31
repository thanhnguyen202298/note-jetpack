package net.thanhnguyen.z_note.core

import java.text.SimpleDateFormat
import java.util.Date

fun dateFormatter(pattern: String) = SimpleDateFormat(pattern)

fun Long.toDateString(format: SimpleDateFormat) = format.format(Date(this))

fun String.getSpacer(fromIndex: Int=0) = if (this.length > 20) {
    this.substring(fromIndex).indexOf(' ') + this.length/4
} else this.length

fun String.replaceFirsUpperCase()
        : String = replaceFirstChar{this[0].uppercase()}


fun String.validate(): String {
    return if (this.isEmpty()) {
        "Name cannot be empty."
    } else if (this.trim().isEmpty()) {
        "Name must have at least 1 letter."
    } else {
        ""
    }
}

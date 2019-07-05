package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs

fun String.truncate(index: Int = 16): String {
    val message = this
    val i = if (message.length < abs(index)) message.length else abs(index)
    val croppedString = when {
        i - 1 == message.lastIndex -> message
        message[i - 1] in " " && message[i - 2] in " " -> message
        message[i - 1] in " " -> "${message.substring(0, i - 1)}..."
        else -> "${message.substring(0, i)}..."
    }
    return croppedString.trim()
}
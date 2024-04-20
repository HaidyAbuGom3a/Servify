package org.haidy.servify.data.util

fun Any?.toStringOrEmpty(): String {
    if(this == null) return ""
    return this.toString()
}
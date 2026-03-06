package com.gradecalculator

import com.gradecalculator.console.ConsoleMode
import com.gradecalculator.web.WebServer

fun main() {
    println("===========================================")
    println("       GRADE CALCULATOR APPLICATION        ")
    println("===========================================")
    println()
    println("Please select a mode:")
    println("  1 - Console Mode")
    println("  2 - Web GUI Mode")
    println()
    print("Please make your  choice (1 or 2): ")

    when (readLine()?.trim()) {
        "1" -> ConsoleMode.start()
        "2" -> WebServer.start()
        else -> {
            println("Invalid choice! Please restart and enter 1 or 2.")
        }
    }
}
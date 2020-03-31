package com.raytool.commands

/**
 *@author Luis Miguel Barcos
 */

class Daily(private val dailyCommand: Command.Daily) {
    fun execute() {
        print("Todo: execute the following command if it's possible\nraytool ")
        dailyCommand.arguments.forEach { print("$it ") }
    }
}
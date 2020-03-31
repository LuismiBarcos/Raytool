package com.raytool.commands

/**
 *@author Luis Miguel Barcos
 */

class Help(private val helpCommand: Command.Help) {
    fun execute() {
        print("Todo: execute the following command if it's possible\nraytool ")
        helpCommand.arguments.forEach { print("$it ") }
    }
}
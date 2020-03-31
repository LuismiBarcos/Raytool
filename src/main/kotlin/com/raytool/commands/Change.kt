package com.raytool.commands

/**
 *@author Luis Miguel Barcos
 */

class Change(private val changeCommand: Command.Change) {
    fun execute() {
        print("Todo: execute the following command if it's possible\nraytool ")
        changeCommand.arguments.forEach { print("$it ") }
    }
}
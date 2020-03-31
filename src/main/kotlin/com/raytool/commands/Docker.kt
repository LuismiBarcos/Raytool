package com.raytool.commands

/**
 *@author Luis Miguel Barcos
 */

class Docker (private val dockerCommand: Command.Docker) {
    fun execute() {
        print("Todo: execute the following command if it's possible\nraytool ")
        dockerCommand.arguments.forEach { print("$it ") }
    }
}
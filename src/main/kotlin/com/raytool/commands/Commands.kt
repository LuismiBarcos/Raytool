package com.raytool.commands

/**
 *@author Luis Miguel Barcos
 */

sealed class Command {
    data class Init(val arguments: List<String>) : Command()
    data class Daily(val arguments: List<String>) : Command()
    data class Docker(val arguments: List<String>) : Command()
    data class Change(val arguments: List<String>) : Command()
    data class Help(val arguments: List<String>) : Command()
}

enum class Commands(val command: String) {
    INIT("init"),
    DAILY("daily"),
    DOCKER("docker"),
    CHANGE("change"),
    HELP("help");

    companion object {
        fun create(command: String, arguments: List<String>): Command {
            return when(command) {
                INIT.command -> Command.Init(arguments)
                DAILY.command -> Command.Daily(arguments)
                DOCKER.command -> Command.Docker(arguments)
                CHANGE.command -> Command.Change(arguments)
                else -> Command.Help(arguments)
            }
        }
    }
}

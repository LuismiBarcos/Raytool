package com.raytool.commands

import java.io.File
import java.io.InputStream
import kotlin.system.exitProcess

/**
 *@author Luis Miguel Barcos
 */

open class Executor{
    /**
     * Execute a command in a specific directory
     * @param directory Directory where the command will be execute
     * @param command List of string which contains the command to execute
     * @return Value of the exit code produced when execute the command
     */
    fun execute(directory: File, vararg command: String) {
        val executionProcess = ProcessBuilder()
            .command(command.toList())
            .directory(directory)
            .start()
        val normalExecutionMessages = Thread { showCommandMessages(executionProcess.inputStream) }
        val errorExecutionMessages = Thread { showCommandMessages(executionProcess.errorStream) }
        normalExecutionMessages.start()
        errorExecutionMessages.start()
        val processExit = executionProcess.waitFor()
        normalExecutionMessages.join()
        errorExecutionMessages.join()
        showMessageCompleted(processExit, command)
        if(processExit != 0) { exitProcess(1) }
    }

    private fun showCommandMessages(stream: InputStream) {
        stream.reader(Charsets.UTF_8).use { print(it.readText()) }
    }

    private fun showMessageCompleted(processExit: Int, command: Array<out String>) {
        print("Command: ")
        command.forEach { print("$it ") }
        println("COMPLETED. Returned with $processExit code\n")
    }
}

sealed class Command: Executor() {
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

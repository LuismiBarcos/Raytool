package com.raytool.commands.business

import com.raytool.commands.contracts.IShellExecutor
import java.io.File
import java.io.InputStream
import kotlin.system.exitProcess

/**
 *@author Luis Miguel Barcos
 */

open class ShellExecutor : IShellExecutor {
    override fun execute(directory: File, vararg command: String) {
        val executionProcess = ProcessBuilder()
            .command(command.toList())
            .directory(directory)
            .start()
        val normalExecutionMessages = Thread { showCommandMessages(executionProcess.inputStream) }
        val errorExecutionMessages = Thread { showCommandMessages(executionProcess.errorStream) }
        normalExecutionMessages.start()
        errorExecutionMessages.start()
        normalExecutionMessages.join()
        errorExecutionMessages.join()
        val processExit = executionProcess.waitFor()
        showMessageCompleted(processExit, command)
        if(processExit != 0) { exitProcess(1) }
    }

    private fun showCommandMessages(stream: InputStream) {
        stream.reader(Charsets.UTF_8).use { it.forEachLine { line -> println(line) } }
    }

    private fun showMessageCompleted(processExit: Int, command: Array<out String>) {
        print("Command: ")
        command.forEach { print("$it ") }
        println("COMPLETED. Returned with $processExit code\n")
    }
}
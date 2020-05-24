package com.raytool.commands.contracts

import java.io.File

/**
 *@author Luis Miguel Barcos
 */
interface IShellExecutor {
    /**
     * Execute a command in a specific directory
     * @param directory Directory where the command will be execute
     * @param command List of string which contains the command to execute
     * @return Value of the exit code produced when execute the command
     */
    fun execute(directory: File, command: List<String>)
}
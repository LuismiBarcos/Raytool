package com.raytool.commands.business

import com.raytool.commands.contracts.Command
import com.raytool.extensions.getWordsBySpaces
import java.io.File

/**
 *@author Luis Miguel Barcos
 */

class Utils(
    private val utilsCommand: Command.Utils,
    private val shellExecutor: ShellExecutor = ShellExecutor()
) {
    fun execute() {
        when(utilsCommand.arguments.contains("-db")) {
            true -> deleteDataBase()
            false -> println("No tasks executed")
        }
    }

    private fun deleteDataBase() {
        val dropDatabaseSQLSyntax =
            "DROP DATABASE IF EXISTS lportal_master".replace(' ', '&')
        val createDatabaseSQLSyntax =
            "CREATE DATABASE lportal_master CHARACTER SET UTF8mb4 COLLATE utf8mb4_bin"
                .replace(' ', '&')
        val dropDatabaseCommand =
            "mysql -u root -proot -e $dropDatabaseSQLSyntax".getWordsBySpaces(useAmpersandAsWhitespace = true)
        val createDatabaseCommand =
            "mysql -u root -proot -e $createDatabaseSQLSyntax".getWordsBySpaces(useAmpersandAsWhitespace = true)

        println("Deleting database")
        shellExecutor.execute(File(System.getProperty("user.home")), dropDatabaseCommand)
        println("Database deleted")
        println("Creating database")
        shellExecutor.execute(File(System.getProperty("user.home")), createDatabaseCommand)
        println("Database created")
    }
}
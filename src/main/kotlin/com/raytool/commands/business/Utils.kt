package com.raytool.commands.business

import com.raytool.commands.contracts.Command
import com.raytool.commands.contracts.MySQLCommands
import com.raytool.configuration.business.BundleConfiguration
import com.raytool.configuration.contracts.IBundleConfiguration
import com.raytool.extensions.getWordsBySpaces
import java.io.File

/**
 *@author Luis Miguel Barcos
 */

class Utils(
    private val utilsCommand: Command.Utils,
    private val bundleConfiguration: IBundleConfiguration = BundleConfiguration()
) {

    fun execute() {
        when(utilsCommand.arguments.contains("-db")) {
            true -> recreateDatabase()
            false -> println("No tasks executed")
        }
    }

    private fun recreateDatabase() {
        deleteDatabase()
        createDatabase()
    }

    fun deleteDatabase() {
        println("Deleting database")
        val dropDB = MySQLCommands
            .getDropDatabaseCommand(
                bundleConfiguration.getDatabaseName(),
                bundleConfiguration.getDatabaseUsername(),
                bundleConfiguration.getDatabasePassword())
            .getWordsBySpaces(useAmpersandAsWhitespace = true)
        utilsCommand.execute(File(System.getProperty("user.home")), dropDB)
        println("Database deleted")
    }

    fun createDatabase() {
        println("Creating database")
        val createDB = MySQLCommands
            .getCreateDatabaseCommand(
                bundleConfiguration.getDatabaseName(),
                bundleConfiguration.getDatabaseUsername(),
                bundleConfiguration.getDatabasePassword())
            .getWordsBySpaces(useAmpersandAsWhitespace = true)
        utilsCommand.execute(File(System.getProperty("user.home")), createDB)
        println("Database created")
    }
}
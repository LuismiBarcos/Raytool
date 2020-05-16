package com.raytool.commands

import com.raytool.configuration.ConfigurationInfo
import com.raytool.configuration.business.Configuration
import com.raytool.configuration.contracts.IConfiguration
import java.io.File
import java.io.FileNotFoundException

/**
 *@author Luis Miguel Barcos
 */

class Daily(private val dailyCommand: Command.Daily, private val configuration: IConfiguration = Configuration()) {
    fun execute() {
        when(configuration.exitsConfigurationFolder()) {
            true -> executeAntAll()
            false -> println("Please, execute raytool init to create the Raytool configuration")
        }
    }

    private fun executeAntAll() {
        try {
            val configurationInfo = configuration.getConfigurationInfo()
            checkoutMasterBranch(configurationInfo)
            pullLastChanges(configurationInfo)
            runAntAll(configurationInfo)
        } catch (ex: FileNotFoundException) {
            println("Configuration not found.\nPlease, run raytool init")
        }
    }

    private fun checkoutMasterBranch(configurationInfo: ConfigurationInfo){
        executor(File(configurationInfo.liferayPortalCEPath),"git", "checkout", "master")
    }

    private fun pullLastChanges(configurationInfo: ConfigurationInfo) {
        executor(File(configurationInfo.liferayPortalCEPath),
            "git", "pull", "--ff-only", "upstream", "master")
    }

    private fun runAntAll(configurationInfo: ConfigurationInfo) {
        executor(File(configurationInfo.liferayPortalCEPath),"ant", "all")
    }

    private fun executor(directory: File, vararg command: String) {
        val processBuilder = ProcessBuilder()
            .command(command.toList())
            .directory(directory)
        val process = processBuilder.start()
        process.inputStream.reader(Charsets.UTF_8).use {
            print(it.readText())
        }
        process.errorStream.reader(Charsets.UTF_8).use {
            print(it.readText())
        }
        process.waitFor()
        print("Command: ")
        command.forEach { print("$it ") }
        println("COMPLETED")
    }
}
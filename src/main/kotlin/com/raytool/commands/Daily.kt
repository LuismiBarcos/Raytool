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
            pushChangesToMaster(configurationInfo)
            runAntAll(configurationInfo)
        } catch (ex: FileNotFoundException) {
            println("Configuration not found.\nPlease, run raytool init")
        }
    }

    private fun checkoutMasterBranch(configurationInfo: ConfigurationInfo) =
        dailyCommand.execute(File(configurationInfo.liferayPortalCEPath),"git", "checkout", "master")

    private fun pullLastChanges(configurationInfo: ConfigurationInfo) =
        dailyCommand.execute(
            File(configurationInfo.liferayPortalCEPath),
            "git", "pull", "--ff-only", "upstream", "master")

    private fun pushChangesToMaster(configurationInfo: ConfigurationInfo) =
        dailyCommand.execute(File(configurationInfo.liferayPortalCEPath), "git", "push", "origin", "master")

    private fun runAntAll(configurationInfo: ConfigurationInfo) =
        dailyCommand.execute(File(configurationInfo.liferayPortalCEPath),"ant", "all")
}
package com.raytool.commands.business

import com.raytool.commands.contracts.Command
import com.raytool.commands.contracts.GitCommands
import com.raytool.commands.contracts.PortalCECommands
import com.raytool.configuration.contracts.ConfigurationInfo
import com.raytool.configuration.business.Configuration
import com.raytool.configuration.contracts.IConfiguration
import com.raytool.extensions.getWordsBySpaces
import java.io.File
import java.io.FileNotFoundException

/**
 *@author Luis Miguel Barcos
 */

class Daily(
    private val dailyCommand: Command.Daily,
    private val configuration: IConfiguration = Configuration()
) {
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
        dailyCommand.execute(File(configurationInfo.liferayPortalCEPath),GitCommands.GIT_CHECKOUT_MASTER.getWordsBySpaces())

    private fun pullLastChanges(configurationInfo: ConfigurationInfo) =
        dailyCommand.execute(
            File(configurationInfo.liferayPortalCEPath),
            GitCommands.GIT_PULL_UPSTREAM.getWordsBySpaces())

    private fun pushChangesToMaster(configurationInfo: ConfigurationInfo) =
        dailyCommand.execute(
            File(configurationInfo.liferayPortalCEPath),
            GitCommands.GIT_PUSH_ORIGIN_MASTER.getWordsBySpaces())

    private fun runAntAll(configurationInfo: ConfigurationInfo) =
        dailyCommand.execute(File(configurationInfo.liferayPortalCEPath), PortalCECommands.ANT_ALL.getWordsBySpaces())
}
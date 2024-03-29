package com.raytool.commands.business

import com.raytool.commands.contracts.Command
import com.raytool.configuration.contracts.Bundle
import com.raytool.configuration.contracts.ConfigurationInfo
import com.raytool.configuration.business.Configuration
import com.raytool.configuration.contracts.IConfiguration

/**
 *@author Luis Miguel Barcos
 */

class Init (
    private val initCommand: Command.Init,
    private val configuration: IConfiguration = Configuration()
) {

    fun execute() {
        initForm()
    }

    private fun initForm() {
        val portalCEPath = askForAPath("Please, enter your Liferay Portal CE path")
        val liferayBundlePath = askForAPath("Please, enter your Liferay Home path (the bundle)")
        val configurationInfo = ConfigurationInfo(
            portalCEPath,
            Bundle(liferayBundlePath)
        )
        println("Setting default values")
        configuration.createConfigurationFile(configurationInfo)
        println("Default values saved. For change the default values re-run this command")
    }

    private tailrec fun askForAPath(messageToShow: String, path: String = ""): String {
        return when(configuration.exitsPath(path)) {
            true -> path
            false -> {
                println(messageToShow)
                return askForAPath("Wrong path. Please, enter again", getInputFromConsole())
            }
        }
    }

    private fun getInputFromConsole(): String = readLine() ?: ""
}
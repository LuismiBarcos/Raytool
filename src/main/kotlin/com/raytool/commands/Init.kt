package com.raytool.commands

import com.raytool.configuration.business.Configuration
import com.raytool.configuration.contracts.IConfiguration

/**
 *@author Luis Miguel Barcos
 */

class Init (private val initCommand: Command.Init, private val configuration: IConfiguration = Configuration()) {

    fun execute() {
        print("Todo: execute the following command if it's possible\nraytool ")
        initCommand.arguments.forEach { print("$it ") }

        initForm()

    }

    private fun initForm() {
        println("Please, enter your Liferay Portal CE path")

    }
}
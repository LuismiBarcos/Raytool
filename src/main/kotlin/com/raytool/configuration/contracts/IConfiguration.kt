package com.raytool.configuration.contracts

import com.raytool.configuration.ConfigurationInfo

/**
 *@author Luis Miguel Barcos
 */

interface IConfiguration{
    fun create()

    fun exitsConfigurationFolder()

    fun createConfigurationFolder()

    fun createConfigurationFile(configurationInfo: ConfigurationInfo)

    fun exitsPath()

}
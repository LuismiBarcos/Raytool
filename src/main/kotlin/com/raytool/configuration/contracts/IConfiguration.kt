package com.raytool.configuration.contracts

/**
 *@author Luis Miguel Barcos
 */

interface IConfiguration{

    fun exitsPath(path: String): Boolean

    fun exitsConfigurationFolder(): Boolean

    fun createConfigurationFile(configurationInfo: ConfigurationInfo)

    fun getConfigurationInfo(): ConfigurationInfo

}